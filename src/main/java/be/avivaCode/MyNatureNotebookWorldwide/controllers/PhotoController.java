package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.PhotoService;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PhotoController {
    private PhotoService photoService;
    private UserService userService;
    private SightingService sightingService;

    @Autowired
    public PhotoController(PhotoService photoService, UserService userService, SightingService sightingService) {
        this.photoService = photoService;
        this.userService = userService;
        this.sightingService = sightingService;
    }

    @GetMapping("/sightingPage/{sightingId}/uploadPhoto")
    public String uploadPhotoButton(@PathVariable Long sightingId, Model model, Authentication authentication){
        return "sightingPage/" + sightingId;
    }

//
////    // handler for delete photo button
//    @GetMapping("/sightingPage/{sightingId}/deletePhoto")
//    public String deletePhotoButton(@PathVariable Long sightingId, @PathVariable Long photoId, Model model, Authentication authentication) {
//        List<Photo> photos = sightingService.getSightingById(sightingId).getPhotos();
//        User sightingUser = sightingService.getSightingById(sightingId).getUser();
//        model.addAttribute("sightingUser", sightingUser);
//        User currentUser;
//       if(authentication != null){
//            currentUser = userService.findUserByEmail(authentication.getName());
//           System.out.println("currentUser " + currentUser);
//           System.out.println("sightingUser " + sightingUser);
//            model.addAttribute("currentUser", currentUser);
//            if (currentUser.equals(sightingUser) && !photos.isEmpty()) {
//                Photo photo = photoService.getAPhotoById(photoId).get();
//                model.addAttribute("photos", photos);
//                model.addAttribute("photo", photo);
//                model.addAttribute("photoId", photo.getPhotoId());
//                return "sightingPage/{sightingId}/deletePhoto";}
//        } return "sightingPage/{sightingId}";
//    }
   // deletes photo
    @PostMapping("{photoId}/deletePhoto")
    public String deletePhoto(@PathVariable Long photoId, Model model, Authentication authentication){
        Photo photo = photoService.getAPhotoById(photoId).get();
        Sighting sighting = photo.getSighting();
        User currentUser;
        User sightingOwner = sighting.getUser();
        System.out.println("delete photo " + sighting);
        model.addAttribute("sightingOwner", sightingOwner);
        model.addAttribute("sightingId", sighting.getSightingId());
        model.addAttribute("photoId", photoId);
        model.addAttribute("photo", photoService.getAPhotoById(photoId));

        if(authentication != null){
            currentUser = userService.findUserByEmail(authentication.getName());
            model.addAttribute("currentUser", currentUser);
            if(currentUser.equals(sightingOwner)){
            photoService.deletePhoto(photoId);
        }}
        return "redirect:/sightingPage/" + sighting.getSightingId();
    }
}
