package be.avivaCode.MyNatureNotebookWorldwide.controllers;

//TODO - put methods in logical order

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.PhotoService;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class SightingController {
    private SightingService sightingService;
    private UserService userService;
    private UploadController uploadController;
    private SightingRepository sightingRepository;
    private PhotoService photoService;


    @Autowired
    public SightingController(SightingService sightingService, UserService userService, UploadController uploadController, SightingRepository sightingRepository, PhotoService photoService) {
            this.sightingService = sightingService;
            this.userService = userService;
            this.sightingRepository = sightingRepository;
            this.uploadController = uploadController;
            this.photoService = photoService;
    }

    /******************* CRUD METHODS **********************/
    // returns page with form to add a sighting
    @GetMapping("/addSighting")
    public String getAddSightingPage(Model model){
        Sighting sighting = new Sighting();
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
        return "addSighting";
    }

    @PostMapping("/addSighting/save")
    public String addSighting(@ModelAttribute("sighting") Sighting sighting,
                              @RequestParam("imageFile") MultipartFile imageFile, Authentication authentication, Model model) throws IOException {
        sighting.setUser(userService.findUserByEmail(authentication.getName()));
        sightingService.createSighting(sighting);
        Photo photo = new Photo();
        if(!imageFile.isEmpty()){
            photo.setFileName(imageFile.getOriginalFilename());
            photo.setSighting(sightingService.getSightingById(sighting.getSightingId()));
            photo.setUser(sightingService.getSightingById(sighting.getSightingId()).getUser());
            sightingService.saveImage(imageFile, photo);
            model.addAttribute("photo", photo);
            model.addAttribute("sighting", sighting);
        }
        return "redirect:/addSighting?success";

    }
    // persists a sighting to the database
//    @PostMapping("/addSighting/save")
//    public ModelAndView addSighting(@ModelAttribute("sighting") Sighting sighting, Long sightingId,
//                                    @RequestParam("imageFile") MultipartFile imageFile, Authentication authentication, Model model) {
//       ModelAndView modelAndView = new ModelAndView();
//       try {
//           sighting.setUser(userService.findUserByEmail(authentication.getName()));
//           sightingService.createSighting(sighting);
//           System.out.println("try before photo");
//       } catch (Exception e){
//           e.printStackTrace();
//           modelAndView.setViewName("/addSighting?success");
//           System.out.println("catch before photo");
//           return modelAndView;
//       }
//       Photo photo = new Photo();
//       try {
//           photo.setFileName(imageFile.getOriginalFilename());
//           System.out.println("add sighting filename "+ photo.getFileName() );
//           photo.setSighting(sightingService.getSightingById(sighting.getSightingId()));
//           photo.setUser(sightingService.getSightingById(sighting.getSightingId()).getUser());
//           sightingService.saveImage(imageFile, photo);
//           System.out.println("add sighting photopath" + photo.getPath()  );
//           model.addAttribute("photo", photo);
//           model.addAttribute("sighting", sighting);
//           System.out.println("try with photo " );
//           modelAndView.setViewName("redirect:/addSighting?success");
//       } catch (IOException e){
//           e.printStackTrace();
//           modelAndView.setViewName("/addSighting");
//           System.out.println("catch with photo ");
//           return modelAndView;
//       }
//       modelAndView.addObject("photo", photo);
//       modelAndView.addObject("sighting", sighting);
//        return modelAndView;
//    }

    // returns all sightings by a specific user
    @GetMapping("/name/{name}")
    public String getAllByUser(Model model, @PathVariable("name") String name){
        Optional<User> user = userService.getUserByUserName(name);
        List<Sighting> showAllByUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByUser);
        //unwraps user from Optional
        userService.getUserByUserName(name).ifPresent(u -> model.addAttribute("user", u));
        model.addAttribute("name", user.get().getUserName());
        return "userSightings";
    }
    // returns all sightings of a specific species
    @GetMapping("/species/{speciesName}")
    public String getAllBySpeciesName(Model model, @PathVariable("speciesName") String speciesName, Sighting sighting) {
        List<Sighting> showAllBySpecies = sightingService.getAllBySpeciesName(speciesName);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightings", showAllBySpecies);
        model.addAttribute("speciesName", speciesName);
        return "species";
    }

    // returns all sightings from a specific continent
    @GetMapping("/continent/{continent}")
    public String getAllByContinent(Model model, @PathVariable("continent")Sighting.Continent continent, String continentString, Sighting sighting){
        continentString = continent.getDisplayValue();
        List<Sighting> showAllByContinent = sightingService.getAllByContinent(sighting.getContinent());
        model.addAttribute("continent", continentString);
        model.addAttribute("sightings", showAllByContinent);
        return "continent";
    }

    // returns all sightings from a specific country
    @GetMapping("/country/{country}")
    public String getAllByCountry(Model model, @PathVariable("country") String country, Sighting sighting){
        List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
        model.addAttribute("country", sighting.getCountry());
        model.addAttribute("sightings", showAllByCountry);
        return "country";
    }

    // returns all sightings by current user
    @GetMapping("/yourSightings")
    public String getAllByCurrentUser(Authentication authentication, Model model){
        User currentUser = userService.findUserByEmail(authentication.getName());
        Optional user = userService.getUserByUserName(currentUser.getUserName());
        List<Sighting> showAllByCurrentUser = sightingService.getAllByUser(user);
        model.addAttribute("sightings", showAllByCurrentUser);
        return "yourSightings";
    }

    // returns page with detailed information about a specific sighting
    @GetMapping("/sightingPage/{sightingId}")
    public String getSpecificSighting( @PathVariable("sightingId") Long sightingId, Model model, Authentication authentication){
        Sighting sighting = sightingService.getSightingById(sightingId);
        hideLocation(sighting, sighting.getLocationHidden(), authentication);
        model.addAttribute("sighting", sighting);
        model.addAttribute("user", sighting.getUser());
        if(!sighting.getPhotos().isEmpty()) {
            model.addAttribute("photos", sighting.getPhotos());
        } else{
            String placeholder = "photo_2022-11-30_16-53-15.jpg";
            Photo photo = new Photo();
            photo.setFileName(placeholder);
            sighting.getPhotos().add(photo);
        }
        return "sightingPage";
    }

    private void hideLocation(Sighting sighting, Boolean locationHidden, Authentication authentication){
        User user = sighting.getUser();
        if(authentication == null && locationHidden == true) {
            sighting.setLocation("Location is hidden.");
            } else if (locationHidden == true && !authentication.getName().equals(user.getEmail())) {
            sighting.setLocation("Location is hidden.");
            } else{
            sighting.setLocation(sighting.getLocation());
        }
    }
    // handler to get page to allow editing of sighting
    //TODO - figure out problem with speciesname autocomplete on species edit page
    @GetMapping("/updateSighting/{sightingId}")
    public String getUpdateSightingPage(@PathVariable("sightingId") Long sightingId, Model model){
        Sighting sighting = sightingService.getSightingById(sightingId);
        model.addAttribute("sightingId", sighting.getSightingId());
        model.addAttribute("sighting", sighting);
        model.addAttribute("countryList", sightingService.getCountryList());
        model.addAttribute("speciesName", sighting.getSpeciesName());
        return "updateSighting";
    }

    @PostMapping("/updateSighting/save")
    public String updateSighting(@Valid @ModelAttribute("sighting") Sighting sighting){
        sightingService.updateSighting(sighting);
        return "redirect:/yourSightings";
    }
    // handler for delete sighting button
    @GetMapping("/yourSightings/{sightingId}/deleteSighting")
    public String deleteSightingsButton(@PathVariable("sightingId") Long id, Model model){
        Sighting sighting = sightingService.getSightingById(id);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingId", sighting.getSightingId());
        return "yourSightings/deleteSighting";
    }
    // deletes sighting
    @PostMapping("/{sightingId}/deleteSighting")
    public String deleteSighting(@ModelAttribute Sighting sighting, @PathVariable ("sightingId") Long sightingId, Authentication authentication, Model model){
        User currentUser = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("sighting", sightingService.getSightingById(sightingId));
        model.addAttribute("sightingId", sightingId);
        System.out.println("sighting service delete " + sightingId + " " + sighting.getSpeciesName());
        sightingService.deleteSighting(sightingId);
        return "redirect:/yourSightings";
    }

    /******************* HANDLER METHODS FOR HTML **********************/

    // handler method to handle home page request
    @GetMapping("/index")
    public String index(Model model){
        List<Sighting> sightings = sightingService.getAllSightings();
        return findPaginated(1, "dateOfSighting",  "desc", model);
    }

    // handler to allow pagination
    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
                                @RequestParam("sortByDate") String sortByDate,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 10;
        Page<Sighting> page = sightingService.findPaginated(pageNumber, pageSize, sortByDate, sortDir);
        List<Sighting> sightings = page.getContent();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortByDate", sortByDate);
//        model.addAttribute("sortByTime", sortByTime);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");
        model.addAttribute("sightings", sightings);
        model.addAttribute("sighting", new Sighting());
        return "index";
    }
    @GetMapping("/sortBy")
    public String sortByButton(Model model, Sighting sighting){
        List<Sighting> sightingsDescending = sightingService.getAllSightings();
        List<Sighting> sightingsAscending = sightingService.getAllSightingsOldestToNewest();
        model.addAttribute("sighting", sighting);
        model.addAttribute("descending", sightingsDescending);
        model.addAttribute("ascending", sightingsAscending);
        return "sortBy";
    }
    // handler method to handle search field request
    @RequestMapping(path = { "/", "/index", "/search"})
    public String home(Model model, Sighting sighting, @Param("species") String speciesName) {
        if (speciesName != null) {
            List<Sighting> sightings = sightingService.searchBySpecies(speciesName);
            model.addAttribute("species", speciesName);
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            return "species";
        } else {
            List<Sighting> sightings = sightingService.getAllSightings();
            model.addAttribute("sighting", sighting);
            model.addAttribute("sightings", sightings);
            return findPaginated(1, "dateOfSighting",  "desc", model);
        }
    }

    // returns the call to api ITIS database for form autocomplete
    @GetMapping("/speciesNameAutocomplete")
    @ResponseBody
    public List<String> speciesNameAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String term){
        List<String> suggestions = sightingService.getSearchedSpeciesNames(term);
        return suggestions;
    }

    // Converts empty strings to null when addSighting form is submitted to prevent IllegalArgumentException when
    // enum-dropdowns are left blank.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
//
//    @PostMapping("/uploadImage")
//    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, Model model){
//        model.addAttribute("sighting", new Sighting());
//        String returnValue = "/addSighting";
//        try {
//            sightingService.saveImage(imageFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error saving photo." + e);
//            returnValue = "error";
//        }
//        return returnValue;
//    }

}



