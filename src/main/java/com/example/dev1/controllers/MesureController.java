package com.example.dev1.controllers;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;
import com.example.dev1.services.BellyMService;
import com.example.dev1.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/healthmesurements")
public class MesureController {
    @Autowired
    BellyMService bellyMService;
    @Autowired
    MeasurementService measurementService;

    // Controllers for Belly measurements
    @GetMapping("/getAllBellyMs")
    public ModelAndView getAllBellyMs(ModelMap modelMap) { // ModelMap is like a Hashmap spring will automatically initialize this for you.
        modelMap.addAttribute("belly", bellyMService.getAllBellyMs());
        return new ModelAndView("mesurementsDisplay", modelMap);
    }

    @GetMapping("/addBellyM")
    public ModelAndView addBellyM(ModelMap modelMap) {
        modelMap.addAttribute("belly",new BellyMesurement());
        return new ModelAndView("addBellyM", modelMap);
    }

    @GetMapping("/{id}/editBellyM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView showBellyM(@PathVariable("id") int id, ModelMap modelMap) {
        BellyMesurement bellyMesurement = bellyMService.findById(id);
        modelMap.addAttribute("belly", bellyMesurement);
        return new ModelAndView("editBellyM", modelMap);
    }

    @GetMapping("/{id}/deleteBellyM")
    public ModelAndView deleteBellyM(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        bellyMService.deleteById(id);
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @PostMapping("/editBellyM")
    public ModelAndView editBellyM(@ModelAttribute BellyMesurement measurement) {
        bellyMService.update(measurement);
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @PostMapping("/addBellyM")
    public ModelAndView addBellyM(@ModelAttribute BellyMesurement measurement) {
        bellyMService.addBellyM(measurement);
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    // ================================================================================================================
    // Controllers for Blood pressure measurements ====================================================================
    // ================================================================================================================
    @GetMapping("/getAllBPresMs")
    public ModelAndView getAllBPresMs(ModelMap modelMap) { // ModelMap is like a Hashmap spring will automatically initialize this for you.
        modelMap.addAttribute("bpres", measurementService.getAllBPresMs());
        return new ModelAndView("bpressDisplay", modelMap);
    }

    @GetMapping("/addBPresM")
    public ModelAndView addBPressM(ModelMap modelMap) {
        modelMap.addAttribute("bpres",new MesureTemplate());
        return new ModelAndView("addBPresM", modelMap);
    }

    @GetMapping("/{id}/editBPresM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView editBPresM(@PathVariable("id") int id, ModelMap modelMap) {
        BloodPressureMesurement measurement = measurementService.findBPresById(id);
        MesureTemplate mesureTemplate = new MesureTemplate();
        mesureTemplate.setHeartBeat(measurement.getHeartBeat());
        mesureTemplate.setBloodPressureUp(measurement.getBloodPressureHigh());
        mesureTemplate.setBloodPressureDown(measurement.getBloodPressureLow());
        mesureTemplate.setMesureId(measurement.getMesureId());
        mesureTemplate.setDay(measurement.getMesureDate().getDayOfMonth());
        mesureTemplate.setMonth(measurement.getMesureDate().getMonthValue());
        mesureTemplate.setYear(measurement.getMesureDate().getYear());
        modelMap.addAttribute("bpres", mesureTemplate);
        return new ModelAndView("editBPresM", modelMap);
    }

    @GetMapping("/{id}/deleteBPresM")
    public ModelAndView delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        measurementService.deleteBPresById(id);
        redirectAttributes.addFlashAttribute("success", true); // this is used to show the toast or the alert bar in our page
        /**
         * If you don't want to send the view but just want the browser redirect to another URL.
         * You can prefix the url with redirect: which will go to the url localhost:????/
         */
        return new ModelAndView("redirect:/healthmesurements/getAllBPresMs");
    }

    @PostMapping("/editBPresM")
    public ModelAndView editBPresM(@ModelAttribute MesureTemplate template) {
        BloodPressureMesurement measurement = new BloodPressureMesurement();
        measurement.setMesureId(template.getMesureId());
        measurement.setMesureDate
                (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        measurement.setHeartBeat(template.getHeartBeat());
        measurement.setBloodPressureHigh(template.getBloodPressureUp());
        measurement.setBloodPressureLow(template.getBloodPressureDown());
        measurementService.updateBPres(measurement);
        return new ModelAndView("redirect:/healthmesurements/getAllBPresMs");
    }

    @PostMapping("/addBPresM")
    public ModelAndView addBPresM(@ModelAttribute MesureTemplate template) {
        BloodPressureMesurement bloodPressureMesurement = new BloodPressureMesurement();
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            bloodPressureMesurement.setMesureDate(LocalDate.now());
        } else {
            bloodPressureMesurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        bloodPressureMesurement.setHeartBeat(template.getHeartBeat());
        bloodPressureMesurement.setBloodPressureHigh(template.getBloodPressureUp());
        bloodPressureMesurement.setBloodPressureLow(template.getBloodPressureDown());
        measurementService.addBPresM(bloodPressureMesurement);
        return new ModelAndView("redirect:/healthmesurements/getAllBPresMs");
    }
}
