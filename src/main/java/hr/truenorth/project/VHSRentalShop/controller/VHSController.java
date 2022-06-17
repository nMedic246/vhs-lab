package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vhs")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class VHSController {

    @Autowired
    @Qualifier("VHSServiceJPA")
    VHSService vhsService;

    @GetMapping("/list")
    public List<VHS> getAllVHS(Principal principal){
        return vhsService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<VHS> getVHSPreview(@PathVariable("id") String id){
        return vhsService.findVHS(Long.parseLong(id));
    }
    @PostMapping("/addVHS")
    public ResponseEntity<Object> addVHS(@Valid  @RequestBody VHS vhs, @Value("${rental.duration}") String  duration){
        System.out.println("Default rental duration is "+duration+"days.");
        VHS newVHS = vhsService.createVHS(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("New VHS successfully added, the id is "+newVHS.getId());
    }

    @PutMapping("/updateVHS")
    public ResponseEntity<Object> updateVHS(@Valid @RequestBody VHS vhs){
        VHS updatedVHS = vhsService.updateVHS(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("VHS successfully updated");
    }

    @DeleteMapping("/removeVHS/{id}")
    public ResponseEntity<Object> removeVHS(@PathVariable("id") String id){
        Long idDeleted = vhsService.deleteVHS(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).body("VHS with the id "+idDeleted+" was deleted successfully.");
    }
}
