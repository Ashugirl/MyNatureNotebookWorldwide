package be.avivaCode.MyNatureNotebookWorldwide.controllers;


import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SightingController {
    private SightingService sightingService;
    private UserServiceImpl userServiceImpl;


    @Autowired
    public SightingController(SightingService sightingService, UserServiceImpl userServiceImpl) {
            this.sightingService = sightingService;
            this.userServiceImpl = userServiceImpl;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(Model model, Sighting sighting){
        List<Sighting> sightings = sightingService.getAllSightings();
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", sightings);
        return "index";
    }

    //TODO THIS WORKS! DON'T TOUCH!
    @GetMapping("/species/{species}")
    public String getAllBySpecies(Model model, @PathVariable("species") String speciesName) {
        List<Sighting> showAllBySpecies = sightingService.getAllBySpecies(speciesName);
        model.addAttribute("sightings", showAllBySpecies);
        System.out.println(speciesName);
        return "species";
    }

    //TODO = THIS WORKS! DON'T TOUCH!
    @GetMapping("/name/{name}")
    public String getAllByUser(Model model, @PathVariable("name") String name){
        Optional<User> user = userServiceImpl.getUserByUserName(name);
        System.out.println(user);
        List<Sighting> showAllByUser = sightingService.getAllByUser(user);
        model.addAttribute("name", user.get().getName());
        model.addAttribute("sightings", showAllByUser);
        System.out.println("Passing getAllByUser in SightingController");
        System.out.println(showAllByUser);
        return "yourPage";
    }
//    @GetMapping("/index/{sightingId}")
//    public String getSpecificSighting(Model model, @PathVariable("sightingId") Long sightingId){
//        Sighting sighting = sightingService.getSightingById(sightingId);
////        model.addAttribute("sightingId", sightingId);
////        model.addAttribute("species", sighting.getSpeciesName());
////        model.addAttribute("country", sighting.getCountry());
////        model.addAttribute("continent", sighting.getContinent());
//        System.out.println(sighting.getSightingId());
//        System.out.println(sighting.getContinent());
//
//        return "sightingPage/{sightingId}";
//    }
//    @GetMapping("/index/{continent}")
//    public String getAllByContinent(Model model, @PathVariable("continentDisplayValue") String continentDisplayValue, Sighting.Continent continent){
//        List<Sighting> showAllByContinent = sightingService.getAllByContinent(continent);
//        System.out.println(continent);
//        System.out.println(showAllByContinent);
//        model.addAttribute("continentDisplayValue", continent.getDisplayValue());
//        model.addAttribute("sightings", showAllByContinent);
//        return "continent";
//    }

    @GetMapping("/country/{country}")
    public String getAllByCountry(Model model, @PathVariable("country") Locale country){
        List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
//        System.out.println(country);
//        System.out.println(showAllByCountry);
        model.addAttribute("country", country);
        model.addAttribute("sightings", showAllByCountry);
        return "country";
    }
    @GetMapping("/addSighting")
    public String getAddSightingPage(Model model){
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
        return "addSighting";
    }



    @PostMapping("/addSighting/save")
    public String addSighting(@ModelAttribute("sighting") Sighting sighting,
                              Authentication authentication) {
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        sighting.setUser(userServiceImpl.findUserByEmail(authentication.getName()));
        sightingService.createSighting(sighting);
        return "redirect:/addSighting?success";
    }

    @GetMapping("/sightingPage")
    public String getSightingPage(Model model){
         List<Sighting> sightings = sightingService.getAllSightings();
            model.addAttribute("sightings", sightings);

        return "sightingPage";
    }

    @GetMapping("/speciesNameAutocomplete")
    @ResponseBody
    public List<String> speciesNameAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String term){
        List<String> suggestions = sightingService.getSearchedSpeciesNames(term);
        return suggestions;
    }


    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }




}



