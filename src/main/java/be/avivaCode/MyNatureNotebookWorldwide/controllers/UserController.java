package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


public class UserController {
//    private UserServiceImpl userServiceImpl;
//    private SightingService sightingService;

//      @Autowired
//    public UserController(UserServiceImpl userServiceImpl, SightingService sightingService) {
//        this.userServiceImpl = userServiceImpl;
//        this.sightingService = sightingService;
//    }
//    @GetMapping("/signUp")
//    public String userRegistrationPage(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        model.addAttribute("passwordsEqual", user.isPasswordsMatch());
//        return "signUp";
//    }
//
//    @PostMapping("/signUp")
//    public String submitSignUpForm(Model model, @Valid User user, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return "signUp";
//        model.addAttribute("user", userServiceImpl.createUser(user));
//        return "redirect:/index";
//
//    }
//
//    @GetMapping("/loginPage")
//    public String getlogin() {
//        return "loginPage";
//    }
//
//
//    @PostMapping("/loginPage")
//    public String userLogin(@ModelAttribute User user, Model model) {
//        User registeredUser = userServiceImpl.authenticateUser(user.getUserName(), user.getPassword());
//        model.addAttribute("userName", registeredUser.getUserName());
//        if (registeredUser == null) {
//            return "loginPage";
//        } else {
//            return "index";
//        }
//    }
//
//
//


}

