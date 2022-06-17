package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.model.Rental;
import hr.truenorth.project.VHSRentalShop.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rental")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class RentalController {

    @Autowired
    RentalService rentalService;

    @PostMapping("/rentVHS")
    public ResponseEntity<Object> addVHS(@RequestBody @Valid RentalForm rentalForm){
        log.info("Post request on /api/rental/rentVHS");
        rentalService.rentVHS(rentalForm);
        return ResponseEntity.status(HttpStatus.OK).body("New rental successfully added!");
    }

    @PostMapping("/returnVHS")
    public ResponseEntity<Object> returnVHS(@RequestBody @Valid ReturnForm returnForm){
        log.info("Post request on /api/rental/returnVHS");
        Rental returnedRental = rentalService.returnVHS(returnForm);
        if(returnedRental.getAdditionalFee() != null){
            return ResponseEntity.status(HttpStatus.OK).body("VHS returned after the due date, you owe us "+ returnedRental.getAdditionalFee()+" kuna!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("VHS returned before the due date, you don't have to pay anything.");
    }

    @GetMapping("/list")
    public List<Rental> getAllRentals(){
        log.info("Get request on /api/rental/list");
        return rentalService.getAllRentals();
    }
}
