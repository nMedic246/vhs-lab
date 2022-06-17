package hr.truenorth.project.VHSRentalShop.service;

import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import hr.truenorth.project.VHSRentalShop.dto.ReturnForm;
import hr.truenorth.project.VHSRentalShop.model.Rental;

import java.util.List;

public interface RentalService {
    Rental rentVHS(RentalForm rental);
    List<Rental> getAllRentals();
    Rental returnVHS(ReturnForm returnForm);

}
