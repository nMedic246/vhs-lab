package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.model.User;
import hr.truenorth.project.VHSRentalShop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class UserController {

    @Autowired
    @Qualifier("userServiceJPA")
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        log.info("Post request on /api/user/create");
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("New user with username "+newUser.getUsername()+" was successfully created!");
    }
}
