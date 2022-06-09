package hr.truenorth.project.VHSRentalShop.service.impl;

import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.repository.VHSRepository;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("VHSServiceJPA")
public class VHSServiceJPA implements VHSService{

    @Autowired
    VHSRepository vhsRepository;

    @Override
    public ResponseEntity<Object> createVHS(VHS vhs) {
        VHS newVHS = vhsRepository.save(vhs);
        return ResponseEntity.status(HttpStatus.OK).body("New VHS successfully added, the id is = "+newVHS.getId());
    }

    @Override
    public List<VHS> getAll() {
        return vhsRepository.findAll();
    }

    @Override
    public Optional<VHS> findVHS(long id) {
        return vhsRepository.findById(id);
    }
}
