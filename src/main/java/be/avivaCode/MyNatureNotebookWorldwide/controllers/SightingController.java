package be.avivaCode.MyNatureNotebookWorldwide.controllers;


import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
public class SightingController {
    private SightingService sightingService;
    private UserService userService;


    @Autowired
    public SightingController(SightingService sightingService, UserService userService) {
        super();
        this.sightingService = sightingService;
        this.userService = userService;

    }

    @GetMapping("/index")
    public String showAllSightings(Model model){
        model.addAttribute("sightings", sightingService.getAllSightings());
        return "index";
    }

    @GetMapping("/{userId}/addSighting")
    public String addSightingForm(Model model, @PathVariable Long userId){
        User user = userService.getCurrentUser(userId);
        model.addAttribute("sighting", new Sighting());
        model.addAttribute("countryList", sightingService.getCountryList());
        model.addAttribute("deceased", new Sighting().isDeceased());
        model.addAttribute("lifer", new Sighting().getLifer());
        model.addAttribute("locationHidden", new Sighting().isLocationHidden());
        model.addAttribute("keepPrivate", new Sighting().isKeepPrivate());

        return "addSighting";
    }



    @PostMapping("/addSighting")
    public String submitSighting(Sighting sighting, @PathVariable Long userId, String speciesCommonName) {
        User user = userService.getCurrentUser(userId);
        sighting = new Sighting(user, speciesCommonName);
       // sighting = new Sighting(sighting.getSightingId(), user, sighting.getContinent(), sighting.getCountry(), "" );
        sightingService.createSighting(sighting);
//        model.addAttribute("user", userService.getCurrentUser(user.getUserId()));
//        model.addAttribute("sighting", sightingService.createSighting(sighting));

        return "redirect:/index";

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



