package be.avivaCode.MyNatureNotebookWorldwide.controllers;


import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


public class SightingController {
    private SightingService sightingService;
    private UserServiceImpl userServiceImpl;


    @Autowired
    public SightingController(SightingService sightingService, UserServiceImpl userServiceImpl) {
            super();
            this.sightingService = sightingService;
            this.userServiceImpl = userServiceImpl;

    }
//
//    @GetMapping("/index")
//    public String showAllSightings(Model model){
//        model.addAttribute("sightings", sightingService.getAllSightings());
//        return "index";
//    }
//
    @GetMapping("/addSighting")
    public String showAddSightingForm(Model model){
        System.out.println("Hello? This is the sighting Controller. Show my form!");
        Sighting sighting = new Sighting();
       // User user = userServiceImpl.getCurrentUser(userId);
        model.addAttribute("sighting", sighting);
//        model.addAttribute("countryList", sightingService.getCountryList());
//        model.addAttribute("deceased", sighting.isDeceased());
//        model.addAttribute("lifer", sighting.getLifer());
//        model.addAttribute("locationHidden", new Sighting().isLocationHidden());
//        model.addAttribute("keepPrivate", new Sighting().isKeepPrivate());
        return "addSighting";
    }



   @PostMapping("/addSighting/save")
    public String submitSighting(@ModelAttribute("sighting") Sighting sighting, BindingResult bindingResult, Long id, String speciesCommonName) {
       System.out.println("Hello from submit Sighting in Sighting controller");
        Optional<User> user = userServiceImpl.getUserById(id);
        sighting = new Sighting();
        sightingService.createSighting(sighting);
//        sighting = new Sighting(user, speciesCommonName);
//       // sighting = new Sighting(sighting.getSightingId(), user, sighting.getContinent(), sighting.getCountry(), "" );
//        sightingService.createSighting(sighting);
////        model.addAttribute("user", userServiceImpl.getCurrentUser(user.getUserId()));
////        model.addAttribute("sighting", sightingService.createSighting(sighting));
//
       return "redirect:/sighting";
//
    }

//    @RequestMapping("/addSighting")
//    public String addSighting(Model model, Sighting sighting, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return "addSighting";
//        model.addAttribute("sighting", sighting);
//        return "addSighting";
//    }
//
//    @GetMapping("/commonNameAutocomplete")
//    @ResponseBody
//    public List<String> commonNameAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "")String term){
//        List<String> suggestions = new ArrayList<>();
//        suggestions.add("Red Maple");
//        suggestions.add("Red Squirrel");
//        suggestions.add("Red Fox");
//        suggestions.add("Red Snapper");
//        return suggestions;
//    }
}



