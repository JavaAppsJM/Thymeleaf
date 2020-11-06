package com.example.dev1.controllers;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.services.BellyMService;
import com.example.dev1.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/healthmeasurements")
public class HTMLController {
    @Autowired
    BellyMService bellyMService;
    @Autowired
    MeasurementService measurementService;

    // Controllers for Belly measurements
    @GetMapping("/getAllGenMs")
    public ModelAndView getAllGenMs(ModelMap modelMap) { // ModelMap is like a Hashmap spring will automatically initialize this for you.
        modelMap.addAttribute("belly", bellyMService.getAllBellyMs());
        return new ModelAndView("mesurementsDisplay", modelMap);
    }

    @GetMapping("/addGenBellyM")
    public ModelAndView addGenBellyM(ModelMap modelMap) {
        String[][] tempList = new String[1][2];
        tempList[0][0] = "Buikomtrek";
        tempList[0][1] = "circumRef";

        HTMLTemplate htmlTemplate = new HTMLTemplate();
        htmlTemplate.sethTitle("Add Generic Measurement");
        htmlTemplate.sethFormActionUrl("@{/healthmeasurements/addGenBellyM}");
        htmlTemplate.sethList(tempList);
        modelMap.addAttribute("GenMap", htmlTemplate);
        return new ModelAndView("addGenM", modelMap);
    }

    @GetMapping("/{id}/editGenM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView editGenM(@PathVariable("id") int id, ModelMap modelMap) {
        BellyMesurement bellyMesurement = bellyMService.findById(id);
        modelMap.addAttribute("belly", bellyMesurement);
        return new ModelAndView("editGenM", modelMap);
    }

    @GetMapping("/{id}/deleteGenM")
    public ModelAndView deleteGenM(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        bellyMService.deleteById(id);
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @PostMapping("/editGenM")
    public ModelAndView editPostGenM(@ModelAttribute HTMLTemplate template) {
        bellyMService.update(measurementService.moveTemplateInBelly(template));
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @PostMapping("/addGenM")
    public ModelAndView addPostGenM(@ModelAttribute HTMLTemplate template) {
        bellyMService.addBellyM(measurementService.moveTemplateInBelly(template));
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    // ================================================================================================================
    // Controllers for Blood pressure measurements ====================================================================
    // ================================================================================================================

    @GetMapping("/addGenBPresM")
    public ModelAndView addGenBPresM(ModelMap modelMap) {
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
        modelMap.addAttribute("GenMap", htmlTemplate);
        return new ModelAndView("addGenM", modelMap);
    }

    @PostMapping("/addGenBPresM")
    public ModelAndView addPostGenBPresM(@ModelAttribute HTMLTemplate template) {
        bellyMService.addBellyM(measurementService.moveTemplateInBelly(template));
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

}
