package com.example.dev1.services;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;
import com.example.dev1.repositories.BPresMRepository;
import com.example.dev1.repositories.BellyMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {
    @Autowired
    BellyMRepository bellyMRepo;
    BPresMRepository bpresMRepo;

    // Belly services
    public List<BellyMesurement> getAllBellyMs(){
        return bellyMRepo.getAllBellyMs();
    }

    public void addBellyM(BellyMesurement bellyMesurement) {
        bellyMRepo.addBellyM(bellyMesurement);
    }

    public BellyMesurement findById(int id) {
        return bellyMRepo.findById(id);
    }

    public void update(BellyMesurement bellyMesurement) {
        bellyMRepo.update(bellyMesurement);
    }

    public void deleteById(int id) {
        bellyMRepo.deleteById(id);
    }

    // Blood pressure services
    public List<BloodPressureMesurement> getAllBPresMs(){
        return bpresMRepo.getAllBPresMs();
    }

    public void addBPresM(BloodPressureMesurement measurement) {
        bpresMRepo.addBPresM(measurement);
    }

    public BloodPressureMesurement findBPresById(int id) {
        return bpresMRepo.findBPresById(id);
    }

    public void updateBPres(BloodPressureMesurement measurement) {
        bpresMRepo.updateBPres(measurement);
    }

    public void deleteBPresById(int id) {
        bpresMRepo.deleteBPresById(id);
    }
}
