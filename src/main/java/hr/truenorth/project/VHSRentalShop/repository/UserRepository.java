package hr.truenorth.project.VHSRentalShop.repository;

import hr.truenorth.project.VHSRentalShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
