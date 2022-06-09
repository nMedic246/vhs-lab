package hr.truenorth.project.VHSRentalShop.repository;

import hr.truenorth.project.VHSRentalShop.model.Rental;
import hr.truenorth.project.VHSRentalShop.model.id.RentalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {
    Rental findByIdVHSIdAndUserUsername(long id,String username);
}
