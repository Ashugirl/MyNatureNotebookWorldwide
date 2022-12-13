package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.dto.UserDto;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.PhotoService;
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
import java.util.ArrayList;
import java.util.Comparator;
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
        photos.sort(Comparator.comparing(Photo::getSightingDate).reversed());
        List<Sighting> lifers = sightingService.getAllLifersForUser(Optional.of(currentUser));
        model.addAttribute("userId", currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("name", currentUser.getUserName());
        model.addAttribute("photos", photos);
        model.addAttribute("lifers", lifers);
        for(Sighting s : lifers){
            LocalDateTime dateTime = s.getDateOfSighting();
            model.addAttribute("s", s);
            model.addAttribute("dateOfSighting", dateTime);
        }
        for(Photo photo: photos){
            LocalDateTime dateTime = photo.getSightingDate();
            model.addAttribute("sighting", photo.getSighting());
            model.addAttribute("sightingDate", dateTime);
        }
        return "profile";
    }


    // handler method to handle user update form request
    @GetMapping("/editUser/{userId}")
    public String editUserForm(Model model, @PathVariable("userId") Long id){
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
    public String updateUser(@Valid @ModelAttribute("user") UserDto userDto,/* @PathVariable("userId") Long userId,*/ Model model){
        model.addAttribute("userId", userDto.getId());
        userService.updateUser(userDto);
        return "redirect:/profile";
    }

    @GetMapping("/profile/deleteButton")
    public String deleteUserOption(Model model){
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
    public String userLifeList(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        List<Sighting> lifers = sightingService.getAllLifersForUser(Optional.ofNullable(user));
        model.addAttribute("lifers", lifers);
        for(Sighting s : lifers){
            LocalDateTime dateTime = s.getDateOfSighting();
            model.addAttribute("sighting", s);
            model.addAttribute("dateOfSighting", dateTime);
        }
        return "profile";
    }
    @GetMapping("/profile/wishList")
    public String userWishList(Model model, UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        List<String> wishList = user.getWishList();
        model.addAttribute("wishlist", wishList);
        for(String s : wishList){
            model.addAttribute("speciesName", s);
        }
        return "profile";
    }

    @PostMapping("/species/{speciesName}/addToWishList")
    public String saveToWishList(Model model, @PathVariable("speciesName") String speciesName, UserDto userDto){
        User user = userService.findUserByEmail(userDto.getEmail());
        model.addAttribute("user", user);
        List<String> wishList = user.getWishList();
        model.addAttribute("wishList", wishList);
        wishList.add(speciesName);
        user.setWishList(wishList);
        System.out.println("UC " + user.getUserName());
        System.out.println("UC " + wishList.toString());
        userService.saveUser(userDto);

        return "redirect:/profile";

    }
}
