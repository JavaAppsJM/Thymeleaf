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
    public HTMLTemplate fillBellyInHtmlTemplate(BellyMesurement bellyMesurement){
        String[][] tempList = new String[1][2];
        tempList[0][0] = "Buikomtrek";
        tempList[0][1] = "circumRef";
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        htmlTemplate.sethTitle("Add Generic Measurement");
        htmlTemplate.sethFormActionUrl("@{/healthmeasurements/addGenBellyM}");
        htmlTemplate.sethList(tempList);
        htmlTemplate.setBellyMesurement(bellyMesurement);
//        htmlTemplate.setCircumRef(bellyMesurement.getCircumRef());
//        htmlTemplate.setMesureId(bellyMesurement.getMesureId());
        htmlTemplate.setDay(bellyMesurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(bellyMesurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(bellyMesurement.getMesureDate().getYear());

        return htmlTemplate;
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
    public HTMLTemplate fillBPresInHtmlTemplate(BloodPressureMesurement measurement){
        String[][] tempList = new String[3][2];
        tempList[0][0] = "Hartslag";
        tempList[0][1] = "HeartBeat";
        tempList[1][0] = "Bloeddruk bovendruk";
        tempList[1][1] = "bloodPressureHigh";
        tempList[2][0] = "Bloeddruk onderdruk";
        tempList[2][1] = "bloodPressureLow";
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        htmlTemplate.sethTitle("Add Generic Blood Pressure Measurement");
        htmlTemplate.sethFormActionUrl("@{/healthmeasurements/addGenBPresM}");
        htmlTemplate.sethList(tempList);
        htmlTemplate.setBloodPressureMesurement(measurement);
//        htmlTemplate.setHeartBeat(measurement.getHeartBeat());
//        htmlTemplate.setMesureId(measurement.getMesureId());
        htmlTemplate.setDay(measurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(measurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(measurement.getMesureDate().getYear());

        return htmlTemplate;
    }

    public BloodPressureMesurement moveTemplateInBPres(HTMLTemplate template){
        BloodPressureMesurement measurement = new BloodPressureMesurement();
        measurement.setMesureId(template.getMesureId());
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            measurement.setMesureDate(LocalDate.now());
        } else {
            measurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        measurement.setHeartBeat(template.getHeartBeat());
        measurement.setBloodPressureHigh(template.getBloodPressureHigh());
        measurement.setBloodPressureLow(template.getBloodPressureLow());

        return measurement;
    }

    // Blood Pressure repo services
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
