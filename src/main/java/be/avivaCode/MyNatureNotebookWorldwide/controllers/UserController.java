package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.dto.UserDto;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String getUserProfilePage(Model model, Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("userId", currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("name", currentUser.getUserName());
        model.addAttribute("photos", currentUser.getUserPhotos());
        return "profile";
    }


//
//    @GetMapping("/yourPage")
//    public String getYourPage(Model model){
//        return "yourPage";
//    }

    // handler method to handle user update form request
    @GetMapping("/editUser/{userId}")
    public String editUserForm(Model model, @PathVariable("userId") Long id, String email){
        // create model object to store form data
        User user = userRepository.getReferenceById(id);
        model.addAttribute("userId", user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());

        return "editUser";
    }

    // handler method to handle user update form submit request
    @PostMapping("/editUser/{userId}/save")
    public String updateUser(@Valid @ModelAttribute("user") UserDto userDto, @PathVariable("userId") Long id, Model model){
        model.addAttribute("userId", userDto.getId());
        userService.updateUser(userDto);
        return "redirect:/profile";
    }

    //TODO - figure out how to get a modal or other type of check before deleting
    @GetMapping("/profile/deleteButton")
    public String deleteUserOption(Model model, @PathVariable("userId") Long id){
        UserDto user = new UserDto();
        model.addAttribute("userId", user.getId());
        model.addAttribute("user", user);
        return "profile/deleteButton";
    }
//TODO - figure out problem with BeanPropertyBindingResult errors when trying to delete
    @PostMapping("/profile/delete")
    public String deleteUser(@Valid @ModelAttribute("user") User user, Authentication authentication){
        user = userRepository.findByEmail(authentication.getName());
       // System.out.println("CONTROLLER DELETE METHOD " + user.getUserName() + " " + user.getEmail());
        userService.deleteUser(user);
        return "redirect:/logout";
    }
}
