package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.dto.UserDto;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  // handler method to handle user registration form request
  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    // create model object to store form data
    UserDto user = new UserDto();
    model.addAttribute("user", user);
    return "register";
  }

  // handler method to handle user registration form submit request
  @PostMapping("/register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
      Model model) {
    User existingUser = userService.findUserByEmail(userDto.getEmail());
    if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail()
        .isEmpty()) {
      result.rejectValue("email", null, "There is already an account registered with this email.");
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userDto);
      return "/register";
    }
    userService.saveUser(userDto);
    return "redirect:/register?success";

  }

  // handler method to handle list of users
  @GetMapping("/users")
  public String users(Model model) {
    List<UserDto> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "users";
  }

  @GetMapping("/login")
  public String login(Authentication authentication, Model model) {
    if (authentication != null) {
      User user = userService.findUserByEmail(authentication.getName());
      model.addAttribute("user", user);
      return "profile";
    }
    return "login";
  }

}