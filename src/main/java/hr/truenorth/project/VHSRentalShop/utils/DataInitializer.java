package hr.truenorth.project.VHSRentalShop.utils;

import hr.truenorth.project.VHSRentalShop.model.User;
import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.service.UserService;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class DataInitializer {

    @Autowired
    VHSService vhsService;

    @Autowired
    UserService userService;

   @EventListener
    public void appReady(ApplicationReadyEvent event) {
       //createVHS();
        //createUsers();
    }

    private void createVHS(){
        VHS newVHS = new VHS("Back to the future",new Time(1,56,0),1985);
        vhsService.createVHS(newVHS);
        newVHS = new VHS("Se7en",new Time(2,7,0),1995);
        vhsService.createVHS(newVHS);
        newVHS = new VHS("Mean girls", new Time(1,37,0),2004);
        vhsService.createVHS(newVHS);
    }

    /*private void createUsers(){
       User newUser = new User("nika123","nika","medic","qwertz","ROLE_ADMIN");
       userService.createUser(newUser);
       newUser = new User("jonny123","John","Doe","johhnyboi","ROLE_USER");
       userService.createUser(newUser);
       newUser = new User("yozo","Jozo","Jurišić","qwertz","ROLE_USER");
       userService.createUser(newUser);
    }*/
}
