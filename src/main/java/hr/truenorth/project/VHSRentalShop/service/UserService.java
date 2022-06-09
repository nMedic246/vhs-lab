package hr.truenorth.project.VHSRentalShop.service;

import hr.truenorth.project.VHSRentalShop.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    ResponseEntity<Object> createUser(User user);
    Optional<User> findUser(String username);
}
