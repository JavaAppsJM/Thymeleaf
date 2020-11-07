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
        modelMap.addAttribute("belly", measurementService.getAllBellyMs());
        return new ModelAndView("mesurementsDisplay", modelMap);
    }

    @GetMapping("/addGenBellyM")
    public ModelAndView addGenBellyM(ModelMap modelMap) {
        modelMap.addAttribute("GenMap", measurementService.fillBellyInHtmlTemplate(new BellyMesurement()));
        return new ModelAndView("addGenM", modelMap);
    }

    @GetMapping("/{id}/editGenM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView editGenM(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("GenMap", measurementService.fillBellyInHtmlTemplate(measurementService.findById(id)));
        return new ModelAndView("editGenM", modelMap);
    }

    @GetMapping("/{id}/deleteGenM")
    public ModelAndView deleteGenM(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        measurementService.deleteById(id);
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
        modelMap.addAttribute("GenMap", measurementService.fillBPresInHtmlTemplate(new BloodPressureMesurement()));
        return new ModelAndView("addGenM", modelMap);
    }

    @PostMapping("/addGenBPresM")
    public ModelAndView addPostGenBPresM(@ModelAttribute HTMLTemplate template) {
        bellyMService.addBellyM(measurementService.moveTemplateInBelly(template));
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @GetMapping("/{id}/editGenBPresM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView editGenBPresM(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("GenMap", measurementService.fillBPresInHtmlTemplate(measurementService.findBPresById(id)));
        return new ModelAndView("editGenM", modelMap);
    }

    @GetMapping("/{id}/deleteGenBPresM")
    public ModelAndView deleteGenBPresM(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        measurementService.deleteBPresById(id);
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }

    @PostMapping("/editGenBPresM")
    public ModelAndView editPostGenBPresM(@ModelAttribute HTMLTemplate template) {
        measurementService.updateBPres(measurementService.moveTemplateInBPres(template));
        return new ModelAndView("redirect:/healthmesurements/getAllBellyMs");
    }


}
