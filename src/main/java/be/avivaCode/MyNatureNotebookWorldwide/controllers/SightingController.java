package be.avivaCode.MyNatureNotebookWorldwide.controllers;


import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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


    // handler method to handle home page request
    @GetMapping("/index")
    public String home(Model model){
        List<Sighting> sightings = sightingService.getAllSightings();
        model.addAttribute("sightings", sightings);
        System.out.println("hi. Do you see the sightings?");
        return "index";
    }
//    @GetMapping("/index/showAllSightings")
//    public String showAllSightings(Model model){
//        List<Sighting> sightings = sightingService.getAllSightings();
//        model.addAttribute("sightings", sightings);
//        System.out.println("hi. Do you see the sightings?");
//        return "showAllSightings";
//    }
//
//    @GetMapping("/addSighting")
//    public String showAddSightingForm(Model model){
//        System.out.println("Hello? This is the sighting Controller. Show my form!");
//        Sighting sighting = new Sighting();
//        Map<String, String> speciesAutofill = new HashMap<>();
//        Map<String, String> speciesNames = sightingService.getSearchedCommonNames("");
//        for(Map.Entry<String, String> entry : speciesNames.entrySet()){
//            String scientificName = entry.getKey();
//            String commonNames = entry.getValue();
//            speciesAutofill.put(scientificName, commonNames);
//                                }
//       // User user = userServiceImpl.getCurrentUser(userId);
//        model.addAttribute("sighting", sighting);
//        model.addAttribute("speciesAutofill", speciesAutofill);
////        model.addAttribute("countryList", sightingService.getCountryList());
////        model.addAttribute("deceased", sighting.isDeceased());
////        model.addAttribute("lifer", sighting.getLifer());
////        model.addAttribute("locationHidden", new Sighting().isLocationHidden());
////        model.addAttribute("keepPrivate", new Sighting().isKeepPrivate());
//        return "addSighting";
//    }
//
//
//
//   @PostMapping("/addSighting")
//    public String submitSighting(@ModelAttribute("sighting") Sighting sighting, BindingResult bindingResult, Long id, String speciesCommonName) {
//       System.out.println("Hello from submit Sighting in Sighting controller");
//        Optional<User> user = userServiceImpl.getUserById(id);
//        sighting = new Sighting();
//        sightingService.createSighting(sighting);
////        sighting = new Sighting(user, speciesCommonName);
////       // sighting = new Sighting(sighting.getSightingId(), user, sighting.getContinent(), sighting.getCountry(), "" );
////        sightingService.createSighting(sighting);
//////        model.addAttribute("user", userServiceImpl.getCurrentUser(user.getUserId()));
//////        model.addAttribute("sighting", sightingService.createSighting(sighting));
////
//       return "redirect:/sighting";
////
//    }
//
////    @RequestMapping("/addSighting")
////    public String addSighting(Model model, Sighting sighting, BindingResult bindingResult){
////        if(bindingResult.hasErrors())
////            return "addSighting";
////        model.addAttribute("sighting", sighting);
////        return "addSighting";
////    }
////
////    @GetMapping("/commonNameAutocomplete")
////    @ResponseBody
////    public List<String> commonNameAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "")String term){
////        List<String> suggestions = new ArrayList<>();
////        suggestions.add("Red Maple");
////        suggestions.add("Red Squirrel");
////        suggestions.add("Red Fox");
////        suggestions.add("Red Snapper");
////        return suggestions;
////    }
}



