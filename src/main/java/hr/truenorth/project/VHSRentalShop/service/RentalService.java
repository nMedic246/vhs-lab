package hr.truenorth.project.VHSRentalShop.service;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalService {
    ResponseEntity<Object> rentVHS(RentalForm rental);
    List<Rental> getAllRentals();
    ResponseEntity<Object> returnVHS(ReturnForm returnForm);

}
