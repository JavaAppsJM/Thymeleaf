package com.example.dev1.controllers;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.services.BellyMService;
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

    @GetMapping("/getAllMs")
    public ModelAndView getAllMs(ModelMap modelMap) { // ModelMap is like a Hashmap spring will automatically initialize this for you.
        modelMap.addAttribute("belly", bellyMService.getAllBellyMs());

        /* the first parameter of the ModelAndView constructor must be the exact name of your view so mesurementsDisplay, which will fetch mesurementsDisplay.html
         * the second parameter is your modelMap which contains all the data that it needs to fill in our html page
         * */
        return new ModelAndView("mesurementsDisplay", modelMap);
    }

    @GetMapping("/addBellyM")
    public ModelAndView showAddView(ModelMap modelMap) {
        modelMap.addAttribute("belly",new MesureTemplate());
        return new ModelAndView("addBellyM", modelMap);
    }

    @GetMapping("/{id}/editBellyM") // <---- Creates url in the form of localhost:port/healthmesurements/{id}/edit
    public ModelAndView showEditPage(@PathVariable("id") int id, ModelMap modelMap) {
        BellyMesurement bellyMesurement = bellyMService.findById(id);
        MesureTemplate mesureTemplate = new MesureTemplate();
        mesureTemplate.setCircumRef(bellyMesurement.getCircumRef());
        mesureTemplate.setMesureId(bellyMesurement.getMesureId());
        mesureTemplate.setDay(bellyMesurement.getMesureDate().getDayOfMonth());
        mesureTemplate.setMonth(bellyMesurement.getMesureDate().getMonthValue());
        mesureTemplate.setYear(bellyMesurement.getMesureDate().getYear());
        modelMap.addAttribute("belly", mesureTemplate);
        return new ModelAndView("editBellyM", modelMap);
    }

    @GetMapping("/{id}/deleteBellyM")
    public ModelAndView delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        bellyMService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", true); // this is used to show the toast or the alert bar in our page
        /**
         * If you don't want to send the view but just want the browser redirect to another URL.
         * You can prefix the url with redirect: which will go to the url localhost:????/employee/getAllEmployees
         */
        return new ModelAndView("redirect:/healthmesurements/getAllMs");
    }

    @PostMapping("/editBellyM")
    public ModelAndView editBellyM(@ModelAttribute MesureTemplate template) {
        BellyMesurement bellyMesurement = new BellyMesurement();
        bellyMesurement.setMesureId(template.getMesureId());
        bellyMesurement.setMesureDate
                (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        bellyMesurement.setCircumRef(template.getCircumRef());
        bellyMService.update(bellyMesurement);
        return new ModelAndView("redirect:/healthmesurements/getAllMs");
    }

    @PostMapping("/addBellyM")
    public ModelAndView addBellyM(@ModelAttribute MesureTemplate template) {
        BellyMesurement bellyMesurement = new BellyMesurement();
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            bellyMesurement.setMesureDate(LocalDate.now());
        } else {
            bellyMesurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        bellyMesurement.setCircumRef(template.getCircumRef());
        bellyMService.addBellyM(bellyMesurement);
        return new ModelAndView("redirect:/healthmesurements/getAllMs");
    }

}
