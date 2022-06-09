package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.model.Rental;
import hr.truenorth.project.VHSRentalShop.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return rentalService.rentVHS(rentalForm);
    }

    @PostMapping("/returnVHS")
    private ResponseEntity<Object> returnVHS(@RequestBody @Valid ReturnForm returnForm){
        return rentalService.returnVHS(returnForm);
    }

    @GetMapping("/list")
    private List<Rental> getAllRentals(){
        return rentalService.getAllRentals();
    }
}
