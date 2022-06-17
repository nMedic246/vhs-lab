package hr.truenorth.project.VHSRentalShop.service.impl;

import hr.truenorth.project.VHSRentalShop.exception.VHSNotFoundException;
import hr.truenorth.project.VHSRentalShop.model.VHS;
import hr.truenorth.project.VHSRentalShop.repository.VHSRepository;
import hr.truenorth.project.VHSRentalShop.service.VHSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("VHSServiceJPA")
@Slf4j
public class VHSServiceJPA implements VHSService{

    @Autowired
    VHSRepository vhsRepository;

    @Override
    public VHS createVHS(VHS vhs) {
        return vhsRepository.save(vhs);
    }

    @Override
    public List<VHS> getAll() {
        return vhsRepository.findAll();
    }

    @Override
    public Optional<VHS> findVHS(long id) {
        return vhsRepository.findById(id);
    }

    @Override
    public VHS updateVHS(VHS vhs) {
        if(!vhsRepository.existsById(vhs.getId())){
            log.error("VHS with the given id does not exist!");
            throw new VHSNotFoundException("VHS with the given id does not exist!");
        }
        return vhsRepository.save(vhs);
    }

    @Override
    public Long deleteVHS(long id) {
        if(!vhsRepository.existsById(id)){
            log.error("VHS with the given id does not exist!");
            throw new VHSNotFoundException("VHS with the given id does not exist!");
        }
        vhsRepository.deleteById(id);
        return id;
    }
}
