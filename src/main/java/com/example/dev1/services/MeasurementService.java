package com.example.dev1.services;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;
import com.example.dev1.repositories.BPresMRepository;
import com.example.dev1.repositories.BellyMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class MeasurementService {
    private MessageSource ms;
    private Locale locale;

    @Autowired
    BellyMRepository bellyMRepo;
    @Autowired
    BPresMRepository bpresMRepo;

    @Autowired
    public void setMs(MessageSource ms) {
        this.ms = ms;
    }

    @Value("#{T(java.util.Locale).getDefault()}")
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    // Belly services
    public HTMLIntDisplay fillIntDisplayWBelly(List<BellyMesurement> bellyMesurementList) {
        // Prepare col names
        String[] colList = new String[4];
        colList[0] = ms.getMessage("measurementId",new Object[] {}, locale);
        colList[1] = ms.getMessage("measurementDate",new Object[] {}, locale);
        colList[2] = ms.getMessage("circumreference", new Object[] {}, locale);
        colList[3] = ms.getMessage("actions",new Object[] {}, locale);

        // Prepare Content
        String[][] displayList = new String[bellyMesurementList.size()][3];
        int i = 0;
        int j = 0;
        for (BellyMesurement b: bellyMesurementList) {
            displayList[i][j] = String.valueOf(b.getMesureId());
            j++;
            displayList[i][j] = String.valueOf(b.getMesureDate());
            j++;
            displayList[i][j] = String.valueOf(b.getCircumRef());
            j = 0;
            i++;
        }
        HTMLIntDisplay hColTemplate = new HTMLIntDisplay();
        hColTemplate.sethColNames(colList);
        hColTemplate.sethContent(displayList);
        hColTemplate.sethTitle(ms.getMessage("bellydisplayTitle",new Object[] {}, locale));
        hColTemplate.sethDataGroupName("BellyM");

        return hColTemplate;
    }

    public HTMLColumnListD fillBellyColTemplate(List<BellyMesurement> bellyMesurementList){
        String[] colList = new String[4];
        colList[0] = "Measurement Id";
        colList[1] = "Measurement Date";
        colList[2] = "Circumreference";
        colList[3] = "Actions";
        String[][] displayList = new String[bellyMesurementList.size()][3];
        int i = 0;
        int j = 0;
        for (BellyMesurement b: bellyMesurementList) {
            displayList[i][j] = String.valueOf(b.getMesureId());
            j++;
            displayList[i][j] = String.valueOf(b.getMesureDate());
            j++;
            displayList[i][j] = String.valueOf(b.getCircumRef());
            j = 0;
            i++;
        }

        HTMLColumnListD hColTemplate = new HTMLColumnListD();
        hColTemplate.sethList(colList);
        hColTemplate.sethDisplayList(displayList);
        hColTemplate.sethTitle("Display Belly Measurements");
        hColTemplate.sethMType("BellyM");

        return hColTemplate;
    }

    public HTMLIntEdit fillIntEditWBelly(BellyMesurement bellyMesurement, String action){
        // Prepare formfields
        String[][] tempList = new String[1][3];
        tempList[0][0] = ms.getMessage("circumreference", new Object[] {}, locale);
        tempList[0][1] = "circumRef";
        tempList[0][2] = String.valueOf(bellyMesurement.getCircumRef());

        // Fill template
        HTMLIntEdit htmlTemplate = new HTMLIntEdit();
        htmlTemplate.sethTitle(ms.getMessage(action + "bellyTitle", new Object[] {}, locale));
        String formActURL = "/healthmeasurements/" + action + "GenBellyM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethFormContentList(tempList);
        htmlTemplate.setCircumRef(bellyMesurement.getCircumRef());
        htmlTemplate.setMesureId(bellyMesurement.getMesureId());
        if (bellyMesurement.getMesureDate() == null){
            bellyMesurement.setMesureDate(LocalDate.now());
        }
        htmlTemplate.setDay(bellyMesurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(bellyMesurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(bellyMesurement.getMesureDate().getYear());
        htmlTemplate.setLabelMesureDate(ms.getMessage("labelDate", new Object[] {}, locale));
        return htmlTemplate;
    }

    public HTMLTemplate fillBellyInHtmlTemplate(BellyMesurement bellyMesurement, String action){
        String[][] tempList = new String[1][3];
        tempList[0][0] = "Circumreference";
        tempList[0][1] = "circumRef";
        tempList[0][2] = String.valueOf(bellyMesurement.getCircumRef());
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        String pTitle = action.toUpperCase() + " Belly Measurement";
        htmlTemplate.sethTitle(pTitle);
        String formActURL = "/healthmeasurements/" + action + "GenBellyM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethList(tempList);
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

    public BellyMesurement moveIntEditInBelly(HTMLIntEdit template){
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
    public HTMLIntDisplay fillIntDisplayWBPres(List<BloodPressureMesurement> bloodPressureMesurementList) {
        // Prepare col names
        String[] colList = new String[6];
        colList[0] = ms.getMessage("measurementId",new Object[] {}, locale);
        colList[1] = ms.getMessage("measurementDate",new Object[] {}, locale);
        colList[2] = ms.getMessage("heartbeat", new Object[] {}, locale);
        colList[3] = ms.getMessage("bloodpressureh", new Object[] {}, locale);
        colList[4] = ms.getMessage("bloodpressurel", new Object[] {}, locale);
        colList[5] = ms.getMessage("actions",new Object[] {}, locale);

        // Prepare Content
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
        HTMLIntDisplay hColTemplate = new HTMLIntDisplay();
        hColTemplate.sethColNames(colList);
        hColTemplate.sethContent(displayList);
        hColTemplate.sethTitle(ms.getMessage("bpresdisplayTitle",new Object[] {}, locale));
        hColTemplate.sethDataGroupName("BPresM");

        return hColTemplate;
    }

    public HTMLColumnListD fillBPresColTemplate(List<BloodPressureMesurement> bloodPressureMesurementList){
        String[] colList = new String[6];
        colList[0] = "Measurement Id";
        colList[1] = "Measurement Date";
        colList[2] = "Heart Beat";
        colList[3] = "Blood Pressure High";
        colList[4] = "Blood Pressure Low";
        colList[5] = "Actions";
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

        HTMLColumnListD hColTemplate = new HTMLColumnListD();
        hColTemplate.sethList(colList);
        hColTemplate.sethDisplayList(displayList);
        hColTemplate.sethTitle("Display Blood Pressure Measurements");
        hColTemplate.sethMType("BPresM");

        return hColTemplate;
    }

    public HTMLIntEdit fillIntEditWBPres(BloodPressureMesurement measurement, String action){
        // Prepare formfields
        String[][] tempList = new String[3][3];
        tempList[0][0] = ms.getMessage("heartbeat", new Object[] {}, locale);
        tempList[0][1] = "HeartBeat";
        tempList[1][0] = ms.getMessage("bloodpressureh", new Object[] {}, locale);
        tempList[1][1] = "bloodPressureHigh";
        tempList[2][0] = ms.getMessage("bloodpressurel", new Object[] {}, locale);
        tempList[2][1] = "bloodPressureLow";
        tempList[0][2] = String.valueOf(measurement.getHeartBeat());
        tempList[1][2] = String.valueOf(measurement.getBloodPressureHigh());
        tempList[2][2] = String.valueOf(measurement.getBloodPressureLow());

        // Fill template
        HTMLIntEdit htmlTemplate = new HTMLIntEdit();
        htmlTemplate.sethTitle(ms.getMessage(action + "bpresTitle", new Object[] {}, locale));
        String formActURL = "/healthmeasurements/" + action + "GenBPresM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethFormContentList(tempList);
        htmlTemplate.setCircumRef(measurement.getHeartBeat());
        htmlTemplate.setCircumRef(measurement.getBloodPressureHigh());
        htmlTemplate.setCircumRef(measurement.getBloodPressureLow());
        htmlTemplate.setMesureId(measurement.getMesureId());
        if (measurement.getMesureDate() == null){
            measurement.setMesureDate(LocalDate.now());
        }
        htmlTemplate.setDay(measurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(measurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(measurement.getMesureDate().getYear());
        htmlTemplate.setLabelMesureDate(ms.getMessage("labelDate", new Object[] {}, locale));
        return htmlTemplate;
    }

    public HTMLTemplate fillBPresInHtmlTemplate(BloodPressureMesurement measurement, String action){
        String[][] tempList = new String[3][3];
        tempList[0][0] = "Heart Beat";
        tempList[0][1] = "HeartBeat";
        tempList[1][0] = "Blood Upper Pressure";
        tempList[1][1] = "bloodPressureHigh";
        tempList[2][0] = "Blood Lower Pressure";
        tempList[2][1] = "bloodPressureLow";
        tempList[0][2] = String.valueOf(measurement.getHeartBeat());
        tempList[1][2] = String.valueOf(measurement.getBloodPressureHigh());
        tempList[2][2] = String.valueOf(measurement.getBloodPressureLow());
        HTMLTemplate htmlTemplate = new HTMLTemplate();
        String pTitle = action.toUpperCase() + " Blood Pressure Measurement";
        htmlTemplate.sethTitle(pTitle);
        String formActURL = "/healthmeasurements/" + action + "GenBPresM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethList(tempList);
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

    public BloodPressureMesurement moveIntEditInBPres(HTMLIntEdit template){
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
    public BloodPressureMesurement moveTemplateInBPres(HTMLIntEdit template){
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
