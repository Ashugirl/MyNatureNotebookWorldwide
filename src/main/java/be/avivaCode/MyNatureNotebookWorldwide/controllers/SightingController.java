package be.avivaCode.MyNatureNotebookWorldwide.controllers;

//TODO - put methods in logical order

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

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class SightingController {
    private SightingService sightingService;
    private UserService userService;
    private UploadController uploadController;


    @Autowired
    public SightingController(SightingService sightingService, UserService userService) {
            this.sightingService = sightingService;
            this.userService = userService;
    }

    /******************* CRUD METHODS **********************/
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
                              Authentication authentication) {
        sighting.setUser(userService.findUserByEmail(authentication.getName()));
        sightingService.createSighting(sighting);
        return "redirect:/addSighting?success";
    }

    // returns all sightings by a specific user
    @GetMapping("/name/{name}")
    public String getAllByUser(Model model, @PathVariable("name") String name){
        Optional<User> user = userService.getUserByUserName(name);
        List<Sighting> showAllByUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByUser);
        //unwraps user from Optional
        userService.getUserByUserName(name).ifPresent(u -> model.addAttribute("user", u));
        model.addAttribute("name", user.get().getUserName());
        return "userSightings";
    }
    // returns all sightings of a specific species
    @GetMapping("/species/{speciesName}")
    public String getAllBySpeciesName(Model model, @PathVariable("speciesName") String speciesName, Sighting sighting) {
        List<Sighting> showAllBySpecies = sightingService.getAllBySpeciesName(speciesName);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", showAllBySpecies);
        model.addAttribute("speciesName", speciesName);
        return "species";
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
        List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
        model.addAttribute("country", sighting.getCountry());
        model.addAttribute("sightings", showAllByCountry);
        return "country";
    }

    // returns all sightings by current user
    @GetMapping("/yourSightings")
    public String getAllByCurrentUser(Authentication authentication, Model model){
        User currentUser = userService.findUserByEmail(authentication.getName());
        Optional user = userService.getUserByUserName(currentUser.getUserName());
        List<Sighting> showAllByCurrentUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByCurrentUser);
        return "yourSightings";
    }

    // returns page with detailed information about a specific sighting
    @GetMapping("/sightingPage/{sightingId}")
    public String getSpecificSighting(Model model, @PathVariable("sightingId") Long sightingId){
        Sighting sighting = sightingService.getSightingById(sightingId);
        User user = sighting.getUser();
        String userEmail = user.getEmail();
        model.addAttribute("name", userEmail);
        model.addAttribute("user", sighting.getUser());
        model.addAttribute("speciesName", sighting.getSpeciesName());
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingId", sighting.getSightingId());
        if(sighting.getLocationHidden() == true){
            sighting.setLocation("Location hidden");
        }
        return "sightingPage";
    }

    // handler to get page to allow editing of sighting
    //TODO - figure out problem with speciesname autocomplete on species edit page
    @GetMapping("/editSighting/{sightingId}")
    public String getEditSightingPage(@PathVariable("sightingId") Long id, Model model){
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sightingId", sighting.getSightingId());
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
        model.addAttribute("speciesName", sighting.getSpeciesName());
        return "editSighting";
    }
    // updates sighting
    @PostMapping("/editSighting/{sightingId}/save")
    public String updateSighting(@Valid @ModelAttribute("sighting") Sighting sighting,
                                 Authentication authentication, Model model,
                                 @PathVariable ("sightingId")Long sightingId) {
        sighting.setUser(userService.findUserByEmail(authentication.getName()));
        model.addAttribute("sightingId", sightingId);
        sightingService.editSighting(sighting);
        return "redirect:/sightingPage/{sightingId}";
    }
    // handler for delete sighting button
    @GetMapping("/yourSightings/{sightingId}/deleteSighting")
    public String deleteSightingsButton(@PathVariable("sightingId") Long id, Model model){
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingId", sighting.getSightingId());
        return "yourSightings/deleteSighting";
    }
    // deletes sighting
    @PostMapping("/{sightingId}/deleteSighting")
    public String deleteSighting(@ModelAttribute Sighting sighting, @PathVariable ("sightingId") Long sightingId, Authentication authentication, Model model){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("sighting", sightingService.getSightingById(sightingId));
        model.addAttribute("sightingId", sightingId);
        System.out.println("sighting service delete " + sightingId + " " + sighting.getSpeciesName());
        sightingService.deleteSighting(sightingId);
        return "redirect:/yourSightings";
    }

    /******************* HANDLER METHODS FOR HTML **********************/

    // handler method to handle home page request
    @GetMapping("/index")
    public String index(Model model, Sighting sighting, String name){
        List<Sighting> sightings = sightingService.getAllSightings();
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", sightings);
        return "index";
    }
    // handler method to handle search field request
    @RequestMapping(path = { "/", "/index", "/search"})
    public String home(Model model, Sighting sighting, @Param("species") String speciesName) {
        if (speciesName != null) {
            List<Sighting> sightings = sightingService.searchBySpecies(speciesName);
            model.addAttribute("species", speciesName);
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            return "species";
        } else {
            List<Sighting> sightings = sightingService.getAllSightings();
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            return "index";
        }
    }

//
//    private static String encodeValue(String value) {
//        try {
//            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replace("%20", "+");
//        } catch (UnsupportedEncodingException ex) {
//            throw new RuntimeException(ex.getCause());
//        }
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



