package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
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
import java.time.LocalDateTime;
import java.util.List;
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
        List<Photo> photos = currentUser.getUserPhotos();
        model.addAttribute("userId", currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("name", currentUser.getUserName());
        model.addAttribute("photos", photos);
        for(Photo photo: photos){
           model.addAttribute("sighting", photo.getSighting());
        }
        List<Sighting> lifers = sightingService.getAllLifersForUser(Optional.of(currentUser));
        model.addAttribute("lifers", lifers);
        for(Sighting s : lifers){
            LocalDateTime dateTime = s.getDateOfSighting();
            model.addAttribute("s", s);
            model.addAttribute("dateOfSighting", dateTime);
            System.out.println("controller " + s);
        }
        return "profile";
    }


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

    @GetMapping("/profile/deleteButton")
    public String deleteUserOption(Model model, @PathVariable("userId") Long id){
        UserDto user = new UserDto();
        model.addAttribute("userId", user.getId());
        model.addAttribute("user", user);
        return "profile/deleteButton";
    }
    @PostMapping("/profile/delete")
    public String deleteUser(@Valid @ModelAttribute("user") User user, Authentication authentication){
        user = userRepository.findByEmail(authentication.getName());
        userService.deleteUser(user);
        return "redirect:/logout";
    }
    @GetMapping("/profile/lifeList")
    public String userLifeList(@PathVariable("userId") Long id, Model model, User user, Authentication authentication){
        user = userRepository.findByEmail(authentication.getName());
        List<Sighting> lifers = sightingService.getAllLifersForUser(Optional.ofNullable(user));
        model.addAttribute("lifers", lifers);
        for(Sighting s : lifers){
            LocalDateTime dateTime = s.getDateOfSighting();
            model.addAttribute("sighting", s);
            model.addAttribute("dateOfSighting", dateTime);
            System.out.println("controller " + s);
        }
        return "profile";
    }

//    @GetMapping("/profile/wishlist")
}
