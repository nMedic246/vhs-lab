package hr.truenorth.project.VHSRentalShop.service.impl;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.exception.MultipleRentalsException;
import hr.truenorth.project.VHSRentalShop.exception.RentalNotFoundException;
import hr.truenorth.project.VHSRentalShop.exception.UserNotFoundException;
import hr.truenorth.project.VHSRentalShop.exception.VHSNotFoundException;
import hr.truenorth.project.VHSRentalShop.model.Rental;
import hr.truenorth.project.VHSRentalShop.model.User;
import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.model.id.RentalId;
import hr.truenorth.project.VHSRentalShop.repository.RentalRepository;
import hr.truenorth.project.VHSRentalShop.repository.UserRepository;
import hr.truenorth.project.VHSRentalShop.repository.VHSRepository;
import hr.truenorth.project.VHSRentalShop.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RentalServiceJPA implements RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VHSRepository vhsRepository;

    @Value("${rental.duration}")
    private int duration;

    @Value("${rental.fee}")
    private int fee;

    @Value("${rental.rate}")
    private float rate;

    @Override
    public Rental rentVHS(RentalForm rental) {

        if(!userRepository.existsById(rental.getUsername())){
            log.error("User with the given username does not exist!");
            throw new UserNotFoundException("User with the given username does not exist!");
        }
        User user = userRepository.findById(rental.getUsername()).get();
        if(!vhsRepository.existsById(rental.getIdVHS())){
            log.error("VHS with the given id does not exist!");
            throw new VHSNotFoundException("VHS with the given id does not exist!");
        }
        VHS vhs = vhsRepository.findById(rental.getIdVHS()).get();

        if(rentalRepository.existsById(new RentalId(rental.getRentalDate(),rental.getIdVHS()))){
            log.error(vhs.getName()+" was already rented on that day!");
            throw new MultipleRentalsException(vhs.getName()+" was already rented on that day!");
        }
        Date dueDate = calculateDueDate(rental.getRentalDate());
        Rental newRental = new Rental(vhs,rental.getRentalDate(),user,dueDate,fee,false,null);
        return rentalRepository.save(newRental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental returnVHS(ReturnForm returnForm) {
        Rental checkRental = rentalRepository.findByIdVHSIdAndUserUsername(returnForm.getIdVHS(),returnForm.getUsername());
        if(checkRental==null){
            log.error("The given rental was not found.");
            throw new RentalNotFoundException("The given rental was not found.");
        }

        checkRental.setReturned(true);

        if(lateReturnCheck(returnForm.getReturnDate(),checkRental.getDueDate())){
            log.info("VHS was returned past the due date.");
            float additionalFee = calculateFee(checkRental.getDueDate(),returnForm.getReturnDate());
            log.info("Additional fee is "+additionalFee);
            checkRental.setAdditionalFee(additionalFee);
        }
        return rentalRepository.save(checkRental);
    }

    private Date calculateDueDate(Date dateRented){
        log.info("Calculating due date....");
        Calendar c= Calendar.getInstance();
        c.setTime(dateRented);
        c.add(Calendar.DATE, duration);
        log.info("Due date is "+c.getTime());
        return c.getTime();
    }

    private boolean lateReturnCheck(Date returnDate, Date dueDate){
        return returnDate.after(dueDate);
    }

    private float calculateFee(Date dueDate,Date returnDate){
        log.info("Calculating aditional fee...");
        long diff = returnDate.getTime() - dueDate.getTime();
        int days =(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return fee*(1+rate*days)-fee;
    }
}
