package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private SightingService sightingService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, SightingService sightingService, UserRepository userRepository) {
        this.userService = userService;
        this.sightingService = sightingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String getUserProfilePage(Model model, Authentication authentication, String name){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        // Optional user = userServiceImpl.getUserByUserName(currentUser.getName());
        model.addAttribute("name", currentUser.getUserName());

        return "profile";
    }

    @GetMapping("/yourPage")
    public String getYourPage(Model model){
        return "yourPage";
    }

    @GetMapping("/editUser")
    public String getEditUserPage(Model model, Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("userId", currentUser.getId());
        return "editUser";
    }

    @PostMapping("/editUser/save")
    public String updateUser(Model model, Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("userId", currentUser.getId());
        userRepository.save(currentUser);
        return "redirect:/editUser?success";
    }

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
