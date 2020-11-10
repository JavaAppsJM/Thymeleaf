package com.example.dev1.services;

import com.example.dev1.controllers.HTMLColumnListD;
import com.example.dev1.controllers.HTMLTemplate;
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
    @Autowired
    BPresMRepository bpresMRepo;

    // Belly services
    public HTMLColumnListD fillBellyColTemplate(){
        String[] colList = new String[4];
        colList[0] = "Measurement Id";
        colList[1] = "Measurement Date";
        colList[2] = "Circum Reference";
        colList[3] = "Actions";

        HTMLColumnListD hColTemplate = new HTMLColumnListD();
        hColTemplate.sethTitle("Display Belly Measurements");
        hColTemplate.sethList(colList);

        return hColTemplate;
    }

    public HTMLTemplate fillBellyInHtmlTemplate(BellyMesurement bellyMesurement, String action){
        String[][] tempList = new String[1][3];
        tempList[0][0] = "Buikomtrek";
        tempList[0][1] = "circumRef";
        tempList[0][2] = String.valueOf(bellyMesurement.getCircumRef());
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        String pTitle = action.toUpperCase() + " Belly Measurement";
        htmlTemplate.sethTitle(pTitle);
        String formActURL = "/healthmeasurements/" + action + "GenBellyM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethList(tempList);
        htmlTemplate.setBellyMesurement(bellyMesurement);
        htmlTemplate.setCircumRef(bellyMesurement.getCircumRef());
        htmlTemplate.setMesureId(bellyMesurement.getMesureId());
        if (bellyMesurement.getMesureDate() == null){
            bellyMesurement.setMesureDate(LocalDate.now());
        }
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
    public HTMLColumnListD fillBPresColTemplate(List<BloodPressureMesurement> bloodPressureMesurementList){
        String[] colList = new String[6];
        colList[0] = "Measurement Id";
        colList[1] = "Measurement Date";
        colList[2] = "Heart Beat";
        colList[3] = "Blood Pressure High";
        colList[4] = "Blood Pressure Low";
        colList[5] = "Actions";

        HTMLColumnListD hColTemplate = new HTMLColumnListD();
        hColTemplate.sethTitle("Display Blood Pressure Measurements");
        hColTemplate.sethList(colList);

        String[][] displayList = new String[bloodPressureMesurementList.size()][5];
        int i = 0;
        int j = 0;
        for (BloodPressureMesurement b: bloodPressureMesurementList) {
            displayList[i][j] = String.valueOf(b.getMesureId());
            j++;
            displayList[i][j] = String.valueOf(b.getMesureDate());
            j++;
            displayList[i][j] = String.valueOf(b.getHeartBeat());
            j++;
            displayList[i][j] = String.valueOf(b.getBloodPressureHigh());
            j++;
            displayList[i][j] = String.valueOf(b.getBloodPressureLow());
            j = 0;
            i++;
        }
        hColTemplate.sethDisplayList(displayList);

        return hColTemplate;
    }

    public HTMLTemplate fillBPresInHtmlTemplate(BloodPressureMesurement measurement, String action){
        String[][] tempList = new String[3][3];
        tempList[0][0] = "Hartslag";
        tempList[0][1] = "HeartBeat";
        tempList[1][0] = "Bloeddruk bovendruk";
        tempList[1][1] = "bloodPressureHigh";
        tempList[2][0] = "Bloeddruk onderdruk";
        tempList[2][1] = "bloodPressureLow";
        tempList[0][2] = String.valueOf(measurement.getHeartBeat());
        tempList[1][2] = String.valueOf(measurement.getBloodPressureHigh());
        tempList[2][2] = String.valueOf(measurement.getBloodPressureLow());
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        String pTitle = action + " Blood Pressure Measurement";
        htmlTemplate.sethTitle(pTitle);
        String formActURL = "/healthmeasurements/" + action + "GenBPresM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethList(tempList);
        htmlTemplate.setBloodPressureMesurement(measurement);
        htmlTemplate.setHeartBeat(measurement.getHeartBeat());
        htmlTemplate.setBloodPressureHigh(measurement.getBloodPressureHigh());
        htmlTemplate.setBloodPressureLow(measurement.getBloodPressureLow());
        htmlTemplate.setMesureId(measurement.getMesureId());
        if (measurement.getMesureDate() == null){
            measurement.setMesureDate(LocalDate.now());
        }
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
