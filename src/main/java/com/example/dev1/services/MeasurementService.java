package com.example.dev1.services;

import com.example.dev1.controllers.HTMLTemplate;
import com.example.dev1.controllers.MesureTemplate;
import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;
import com.example.dev1.repositories.BPresMRepository;
import com.example.dev1.repositories.BellyMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MeasurementService {
    @Autowired
    BellyMRepository bellyMRepo;
    BPresMRepository bpresMRepo;

    // Belly services
    public MesureTemplate fillBellyTemplate(BellyMesurement bellyMesurement){
        MesureTemplate mesureTemplate = new MesureTemplate();
        mesureTemplate.setCircumRef(bellyMesurement.getCircumRef());
        mesureTemplate.setMesureId(bellyMesurement.getMesureId());
        mesureTemplate.setDay(bellyMesurement.getMesureDate().getDayOfMonth());
        mesureTemplate.setMonth(bellyMesurement.getMesureDate().getMonthValue());
        mesureTemplate.setYear(bellyMesurement.getMesureDate().getYear());

        return mesureTemplate;
    }

    public BellyMesurement moveTemplateInBelly(HTMLTemplate template){
        BellyMesurement bellyMesurement = new BellyMesurement();
        bellyMesurement.setMesureId(template.getMesureId());
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            bellyMesurement.setMesureDate(LocalDate.now());
        } else {
            bellyMesurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        bellyMesurement.setCircumRef(template.getCircumRef());

        return bellyMesurement;
    }

    // Belly reposervices
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
