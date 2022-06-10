package hr.truenorth.project.VHSRentalShop.service;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface VHSService {
    VHS createVHS(VHS vhs);
    List<VHS> getAll();
    Optional<VHS> findVHS(long id);
    VHS updateVHS(VHS vhs);
    Long deleteVHS(long id);
}
