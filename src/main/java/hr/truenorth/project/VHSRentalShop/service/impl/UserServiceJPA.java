package hr.truenorth.project.VHSRentalShop.service.impl;

import hr.truenorth.project.VHSRentalShop.exception.UserAlreadyExistsException;
import hr.truenorth.project.VHSRentalShop.model.User;
import hr.truenorth.project.VHSRentalShop.repository.UserRepository;
import hr.truenorth.project.VHSRentalShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("userServiceJPA")
public class UserServiceJPA implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<Object> createUser(User user) {
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistsException("User with username "+user.getUsername()+" already exists.");
        }
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("New user with username "+newUser.getUsername()+" was successfully created!");
    }

    public Optional<User> findUser(String username) {
        return userRepository.findById(username);
    }
}
