package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.model.Rental;
import hr.truenorth.project.VHSRentalShop.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @PostMapping("/rentVHS")
    private ResponseEntity<Object> addRental(@RequestBody @Valid RentalForm rentalForm){
        rentalService.rentVHS(rentalForm);
        return ResponseEntity.status(HttpStatus.OK).body("New rental successfully added!");
    }

    @PostMapping("/returnVHS")
    private ResponseEntity<Object> returnVHS(@RequestBody @Valid ReturnForm returnForm){
        Rental returnedRental = rentalService.returnVHS(returnForm);
        if(returnedRental.getAdditionalFee() != null){
            return ResponseEntity.status(HttpStatus.OK).body("VHS returned after the due date, you owe us "+ returnedRental.getAdditionalFee()+" kuna!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("VHS returned before the due date, you don't have to pay anything.");
    }

    @GetMapping("/list")
    private List<Rental> getAllRentals(){
        return rentalService.getAllRentals();
    }
}
