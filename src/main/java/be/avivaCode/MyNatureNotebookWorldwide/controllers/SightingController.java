package be.avivaCode.MyNatureNotebookWorldwide.controllers;


import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class SightingController {
    private SightingService sightingService;
    private UserService userServiceImpl;
    private UploadController uploadController;


    @Autowired
    public SightingController(SightingService sightingService, UserService userServiceImpl) {
            this.sightingService = sightingService;
            this.userServiceImpl = userServiceImpl;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String index(Model model, Sighting sighting, String name){
        List<Sighting> sightings = sightingService.getAllSightings();
//        Optional<User> user = userServiceImpl.getUserByUserName(authentication.getName());
//        userServiceImpl.getUserByUserName(name).ifPresent(u ->model.addAttribute("user", u));
//        model.addAttribute("name", user.get().getName());

        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", sightings);
        return "index";
    }
//TODO figure out why search term is coming back null
    @RequestMapping(path = { "/", "/index", "/search"})
    public String home(Model model, Sighting sighting, @Param("species") String speciesName,  String query) {
        if (speciesName != null) {
            List<Sighting> sightings = sightingService.searchBySpecies(speciesName);
            model.addAttribute("species", speciesName);
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            System.out.println("controller if " + sightings);
            return "species";
        } else {
//            model.addAttribute("speciesName", speciesName);
            List<Sighting> sightings = sightingService.getAllSightings();
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            System.out.println("controller else " + sightings);

            return "index";
        }
    }
//    @RequestMapping(path = {"/", "/search"})
//    public String home(Model model, Sighting sighting, @Param("speciesName") String speciesName){
//        List<Sighting> sightingsOfSpecies = sightingService.getAllBySpecies(speciesName);
//        model.addAttribute("sightingsOfSpecies", sightingsOfSpecies);
//        model.addAttribute("speciesName", speciesName);
//        List<Sighting> sightings = sightingService.getAllSightings();
//        model.addAttribute("sighting", sighting);
//        model.addAttribute("sightings", sightings);
//        return "index";
//    }

//    @GetMapping("/{species}")
//    public String search(Model model, @Param("speciesName") String searchTerm){
//        String speciesName = sightingService.encodeValue(searchTerm);
//        List<Sighting> searchedSpeciesSightings = sightingService.getAllBySpecies(speciesName);
//        model.addAttribute("searchedSpeciesSightings", searchedSpeciesSightings);
//        model.addAttribute("speciesName", speciesName);
//        return "redirect:/species/{searchedSpeciesSightings}";
//    }
    // returns page with detailed information about a specific sighting
    @GetMapping("/sightingPage/{sightingId}")
    public String getSpecificSighting(Model model, @PathVariable("sightingId") Long sightingId){
        Sighting sighting = sightingService.getSightingById(sightingId);
        model.addAttribute("user", sighting.getUser());
        model.addAttribute("speciesName", sighting.getSpeciesName());
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingId", sighting.getSightingId());
        if(sighting.getLocationHidden() == true){
            sighting.setLocation("Location hidden");
        }
        return "sightingPage";
    }

    // returns all sightings of a specific species
    @GetMapping("/species/{speciesName}")
    public String getAllBySpeciesName(Model model, @PathVariable("speciesName") String speciesName, Sighting sighting) {
        System.out.println("passing controller " + speciesName);
        List<Sighting> showAllBySpecies = sightingService.getAllBySpeciesName(speciesName);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", showAllBySpecies);
        model.addAttribute("speciesName", speciesName);
        return "species";
    }
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replace("%20", "+");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

        // returns all sightings by a specific user
    @GetMapping("/name/{name}")
    public String getAllByUser(Model model, @PathVariable("name") String name, Sighting sighting){
        Optional<User> user = userServiceImpl.getUserByUserName(name);
        List<Sighting> showAllByUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByUser);
        //unwraps user from Optional
        userServiceImpl.getUserByUserName(name).ifPresent(u -> model.addAttribute("user", u));
        model.addAttribute("name", user.get().getUserName());
        return "userSightings";
    }
    @GetMapping("/yourSightings")
    public String getAllByCurrentUser(Authentication authentication, Model model, String name, Sighting sighting){
            User currentUser = userServiceImpl.findUserByEmail(authentication.getName());
            Optional user = userServiceImpl.getUserByUserName(currentUser.getUserName());
            List<Sighting> showAllByCurrentUser = sightingService.getAllByUser(user);
            model.addAttribute("sightings", showAllByCurrentUser);
            return "yourSightings";

    }

    // returns all sightings from a specific continent
    @GetMapping("/continent/{continent}")
    public String getAllByContinent(Model model, @PathVariable("continent")Sighting.Continent continent, String continentString, Sighting sighting){
        continentString = continent.getDisplayValue();
        List<Sighting> showAllByContinent = sightingService.getAllByContinent(sighting.getContinent());
        model.addAttribute("continent", continentString);
        model.addAttribute("sightings", showAllByContinent);
        return "continent";
    }

    // returns all sightings from a specific country
    @GetMapping("/country/{country}")
    public String getAllByCountry(Model model, @PathVariable("country") String country, Sighting sighting){
        System.out.println("passing controller " + country);
        List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
        model.addAttribute("country", sighting.getCountry());
        model.addAttribute("sightings", showAllByCountry);
        return "country";
    }
    // returns page with form to add a sighting
    @GetMapping("/addSighting")
    public String getAddSightingPage(Model model){
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
       // model.addAttribute("sightingImage", uploadController.displayUploadForm());
        return "addSighting";
    }

    // persists a sighting to the database
    @PostMapping("/addSighting/save")
    public String addSighting(@ModelAttribute("sighting") Sighting sighting,
                              Authentication authentication, Model model) {
        sighting.setUser(userServiceImpl.findUserByEmail(authentication.getName()));
        sightingService.createSighting(sighting);
        return "redirect:/addSighting?success";
    }

//    @GetMapping("/sightingPage")
//    public String getSightingPage(Model model, @PathVariable Long id){
//        Sighting sighting = sightingService.getSightingById(id);
//        model.addAttribute("id", sighting.getSightingId());
//        model.addAttribute("sighting", sighting);
//        model.addAttribute("species", sighting.getSpeciesName());
//        return "sightingPage";
//    }


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



