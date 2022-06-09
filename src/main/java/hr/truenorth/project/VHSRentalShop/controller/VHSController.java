package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vhs")
public class VHSController {

    @Autowired
    @Qualifier("VHSServiceJPA")
    VHSService vhsService;

    @GetMapping("/list")
    private List<VHS> getAllVHS(){
        return vhsService.getAll();
    }

    @PostMapping("/addVHS")
    private ResponseEntity<Object> addVHS(@RequestBody VHS vhs){
        try{
            return vhsService.createVHS(vhs);

        } catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
