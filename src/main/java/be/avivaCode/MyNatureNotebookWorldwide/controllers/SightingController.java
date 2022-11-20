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
    @GetMapping("/")
    public String home(Model model, Sighting sighting){
        List<Sighting> sightings = sightingService.getAllSightings();
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", sightings);
        return "index";
    }

    // returns page with detailed information about a specific sighting
    @GetMapping("/sightingPage/{sightingId}")
    public String getSpecificSighting(Model model, @PathVariable("sightingId") Long sightingId){
        Sighting sighting = sightingService.getSightingById(sightingId);
        return "sightingPage";
    }

    // returns all sightings of a specific species
    @GetMapping("/species/{speciesName}")
    public String getAllBySpecies(Model model, @PathVariable("speciesName") String speciesName, Sighting sighting) {
        List<Sighting> showAllBySpecies = sightingService.getAllBySpecies(speciesName);
        model.addAttribute("sightings", showAllBySpecies);
        model.addAttribute("speciesName",sighting.getSpeciesName());
        return "species";
    }

    // returns all sightings by a specific user
    @GetMapping("/name/{name}")
    public String getAllByUser(Model model, @PathVariable("name") String name, Sighting sighting){
        Optional<User> user = userServiceImpl.getUserByUserName(name);
        List<Sighting> showAllByUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByUser);
        model.addAttribute("user", sighting.getUser());
        model.addAttribute("name", user.get().getName());
        System.out.println("controller " +user);
        System.out.println("controller " + showAllByUser);
        System.out.println("controller " + sighting.getUser());
        System.out.println("controller " + user.get().getName());
        return "yourPage";
    }

    // returns all sightings from a specific continent
    @GetMapping("/continent/{continent}")
    public String getAllByContinent(Model model, @PathVariable("continent")Sighting.Continent continent){
        List<Sighting> showAllByContinent = sightingService.getAllByContinent(continent);
        model.addAttribute("continent", continent);
        model.addAttribute("sightings", showAllByContinent);
        return "continent";
    }

    // returns all sightings from a specific country
    @GetMapping("/country/{country}")
    public String getAllByCountry(Model model, @PathVariable("country") String country){
        List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
        model.addAttribute("country", country);
        model.addAttribute("sightings", showAllByCountry);
        return "country";
    }
    // returns page with form to add a sighting
    @GetMapping("/addSighting")
    public String getAddSightingPage(Model model){
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
        return "addSighting";
    }

    // persists a sighting to the database
    @PostMapping("/addSighting/save")
    public String addSighting(@ModelAttribute("sighting") Sighting sighting,
                              Authentication authentication, String countryName, Locale country) {
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getName());
        sighting.setUser(userServiceImpl.findUserByEmail(authentication.getName()));
        sightingService.createSighting(sighting);
        return "redirect:/addSighting?success";
    }

    @GetMapping("/sightingPage")
    public String getSightingPage(Model model, Long id){
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sighting", sighting);
        model.addAttribute("species", sighting.getSpeciesName());
        return "sightingPage";
    }


    // returns the call to api ITIS database for form autocomplete
    @GetMapping("/speciesNameAutocomplete")
    @ResponseBody
    public List<String> speciesNameAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String term){
        List<String> suggestions = sightingService.getSearchedSpeciesNames(term);
        return suggestions;
    }


    // Converts empty strings to null when addSighting form is submitted to prevent IllegalArgumentException when
    // enum-dropdowns are left blank.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }




}



