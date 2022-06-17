package hr.truenorth.project.VHSRentalShop.controller;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vhs")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@Slf4j
public class VHSController {

    @Autowired
    @Qualifier("VHSServiceJPA")
    VHSService vhsService;

    @GetMapping("/list")
    public List<VHS> getAllVHS(){
        return vhsService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<VHS> getVHSPreview(@PathVariable("id") String id){
        log.info("Get request on /api/vhs/"+id);
        return vhsService.findVHS(Long.parseLong(id));
    }
    @PostMapping("/addVHS")
    public ResponseEntity<Object> addVHS(@Valid  @RequestBody VHS vhs, @Value("${rental.duration}") String  duration){
        log.info("Post request on /api/vhs/addVHS");
        System.out.println("Default rental duration is "+duration+"days.");
        VHS newVHS = vhsService.createVHS(vhs);
        return ResponseEntity.status(HttpStatus.CREATED).body("New VHS successfully added, the id is "+newVHS.getId());
    }

    @PutMapping("/updateVHS")
    public ResponseEntity<Object> updateVHS(@Valid @RequestBody VHS vhs){
        log.info("Put request on /api/vhs/updateVHS");
        VHS updatedVHS = vhsService.updateVHS(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("VHS successfully updated");
    }

    @DeleteMapping("/removeVHS/{id}")
    public ResponseEntity<Object> removeVHS(@PathVariable("id") String id){
        log.info("Delete request on /api/vhs/removeVHS/"+id);
        Long idDeleted = vhsService.deleteVHS(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).body("VHS with the id "+idDeleted+" was deleted successfully.");
    }
}
