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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
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
    public ResponseEntity<Object> rentVHS(RentalForm rental) {

        if(!userRepository.existsById(rental.getUsername())){
            throw new UserNotFoundException("User with the given username does not exist!");
        }
        User user = userRepository.findById(rental.getUsername()).get();
        if(!vhsRepository.existsById(rental.getIdVHS())){
            throw new VHSNotFoundException("VHS with the given id does not exist!");
        }
        VHS vhs = vhsRepository.findById(rental.getIdVHS()).get();

        if(rentalRepository.existsById(new RentalId(rental.getRentalDate(),rental.getIdVHS()))){
            throw new MultipleRentalsException(vhs.getName()+" was already rented today!");
        }
        Date dueDate = calculateDueDate(rental.getRentalDate());
        Rental newRental = new Rental(vhs,rental.getRentalDate(),user,dueDate,fee,false);
        rentalRepository.save(newRental);
        return ResponseEntity.status(HttpStatus.OK).body("New rental successfully added!");
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> returnVHS(ReturnForm returnForm) {
        Rental checkRental = rentalRepository.findByIdVHSIdAndUserUsername(returnForm.getIdVHS(),returnForm.getUsername());
        if(checkRental==null){
            throw new RentalNotFoundException("The given rental was not found.");
        }

        checkRental.setReturned(true);
        rentalRepository.save(checkRental);
        if(lateReturnCheck(returnForm.getReturnDate(),checkRental.getDueDate())){
            float additionalFee = calculateFee(checkRental.getDueDate(),returnForm.getReturnDate());
            return ResponseEntity.status(HttpStatus.OK).body("VHS returned after the due date, you owe us "+ additionalFee+" kuna!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("VHS returned before the due date, nothing to pay!");
        }
    }

    private Date calculateDueDate(Date dateRented){
        Calendar c= Calendar.getInstance();
        c.setTime(dateRented);
        c.add(Calendar.DATE, duration);
        return c.getTime();
    }

    private boolean lateReturnCheck(Date returnDate, Date dueDate){
        return returnDate.after(dueDate);
    }

    private float calculateFee(Date dueDate,Date returnDate){
        long diff = returnDate.getTime() - dueDate.getTime();
        int days =(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return fee*(1+rate*days)-fee;
    }
}
