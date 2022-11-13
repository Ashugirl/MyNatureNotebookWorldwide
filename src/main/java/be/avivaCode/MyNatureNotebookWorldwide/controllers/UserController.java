package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;
    private SightingService sightingService;

    @Autowired
    public UserController(UserService userService, SightingService sightingService) {
        this.userService = userService;
        this.sightingService = sightingService;
    }
    @GetMapping("/signUp")
    public String userRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("passwordsEqual", user.isPasswordsMatch());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String submitSignUpForm(Model model, @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "signUp";
        model.addAttribute("user", userService.createUser(user));
        return "redirect:/index";

    }

//    @GetMapping("/index")
//    public String getlogin() {
//        return "index";
//    }


    @PostMapping("/index")
    public String userLogin(@ModelAttribute User user, Model model, Sighting sighting) {
        User registeredUser = userService.authenticateUser(user.getUserName(), user.getPassword());
        Long sightingId = sighting.getSightingId();
        model.addAttribute("sighting", sightingService.getSightingById(sightingId));
        model.addAttribute("userName", registeredUser.getUserName());
        if (registeredUser == null) {
            return "index";
        } else {
            return "index";
        }
    }





}

