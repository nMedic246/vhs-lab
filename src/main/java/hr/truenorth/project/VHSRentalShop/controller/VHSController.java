package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private ResponseEntity<Object> addVHS(@Valid  @RequestBody VHS vhs){
        VHS newVHS = vhsService.createVHS(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("New VHS successfully added, the id is "+newVHS.getId());
    }

    @PutMapping("/updateVHS")
    private ResponseEntity<Object> updateVHS(@Valid @RequestBody VHS vhs){
        VHS updatedVHS = vhsService.updateVHS(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("VHS successfully updated");
    }

    @DeleteMapping("/removeVHS/{id}")
    private ResponseEntity<Object> removeVHS(@PathVariable("id") String id){
        Long idDeleted = vhsService.deleteVHS(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).body("VHS with the id "+idDeleted+" was deleted successfully.");
    }
}
