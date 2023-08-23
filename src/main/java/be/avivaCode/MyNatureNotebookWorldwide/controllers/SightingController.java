package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.Species;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.service.PhotoService;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SightingController {

  private SightingService sightingService;
  private UserService userService;
  private PhotoService photoService;


  @Autowired
  public SightingController(SightingService sightingService, UserService userService,
      PhotoService photoService) {
    this.sightingService = sightingService;
    this.userService = userService;
    this.photoService = photoService;
  }

  /******************* CRUD METHODS **********************/
  // returns page with form to add a sighting
  @GetMapping("/addSighting")
  public String getAddSightingPage(Model model) {
    Sighting sighting = new Sighting();
    model.addAttribute("sighting", sighting);
    model.addAttribute("countryList", sightingService.getCountryList());
    return "addSighting";
  }

  @PostMapping("/addSighting/save")
  public String addSighting(@ModelAttribute("sighting") Sighting sighting,
      @RequestParam("imageFile") MultipartFile imageFile, Authentication authentication,
      Model model) throws IOException {
    sighting.setUser(userService.findUserByEmail(authentication.getName()));
    sightingService.createSighting(sighting);
    Photo photo = new Photo();
    if (!imageFile.isEmpty()) {
      photo.setFileName(imageFile.getOriginalFilename());
      photo.setSighting(sightingService.getSightingById(sighting.getSightingId()));
      photo.setUser(sightingService.getSightingById(sighting.getSightingId()).getUser());
      sightingService.saveImage(imageFile, photo);
      model.addAttribute("photo", photo);
      model.addAttribute("sighting", sighting);
      return "redirect:/addSighting?success";
    }
    return "redirect:/addSighting?success";
  }

  // returns all sightings by a specific user
  @GetMapping("/name/{name}")
  public String getAllByUser(Model model, @PathVariable("name") String name) {
    Optional<User> user = userService.getUserByUserName(name);
    List<Sighting> showAllByUser = sightingService.getAllByUser(user);
    List<Sighting> publicList = new ArrayList<>();
    for (Sighting s : showAllByUser) {
      if (!s.getKeepPrivate()) {
        publicList.add(s);
        model.addAttribute("sighting", s);
      }
    }
    model.addAttribute("sightings", publicList);
    //unwraps user from Optional
    userService.getUserByUserName(name).ifPresent(u -> model.addAttribute("user", u));
    model.addAttribute("name", user.get().getUserName());
    return "userSightings";
  }

  // returns all sightings of a specific species
  @GetMapping("/species/{speciesName}")
  public String getAllBySpeciesName(Model model, @PathVariable("speciesName") String speciesName,
      Sighting sighting) {
    List<Sighting> showAllBySpecies = sightingService.getAllBySpeciesName(speciesName);
    List<Sighting> publicList = new ArrayList<>();
    for (Sighting s : showAllBySpecies) {
      if (!s.getKeepPrivate()) {
        publicList.add(s);
      }
    }
    model.addAttribute("sighting", sighting);
    model.addAttribute("sightings", publicList);
    model.addAttribute("speciesName", speciesName);
    return "species";
  }

  // returns all sightings from a specific continent
  @GetMapping("/continent/{continent}")
  public String getAllByContinent(Model model,
      @PathVariable("continent") Sighting.Continent continent,
      String continentString, Sighting sighting) {
    continentString = continent.getDisplayValue();
    List<Sighting> showAllByContinent = sightingService.getAllByContinent(sighting.getContinent());
    List<Sighting> publicList = new ArrayList<>();
    for (Sighting s : showAllByContinent) {
      if (!s.getKeepPrivate()) {
        publicList.add(s);
      }
    }
    model.addAttribute("continent", continentString);
    model.addAttribute("sightings", publicList);
    return "continent";
  }

  //TODO : keep trying to get sorting and pagination for each html

  // returns all sightings from a specific continent
//    @GetMapping("/continent/{continent}")
//    public Page<Sighting> getAllByContinent(Model model, @PathVariable("continent")Sighting.Continent continent, @Param("species") String speciesName,
////                                           @PathVariable(value = "pageNumber")
//                                                   Integer pageNumber,
//                                            @RequestParam("sortField") String sortField,
//                                            @RequestParam("sortDir") String sortDir,
//                                            String continentString) {
//        int pageSize = 10;
//        Page<Sighting> page = sightingService.findPaginated(pageNumber, pageSize, sortField, sortDir);
//
//        continentString = continent.getDisplayValue();
//        List<Sighting> showAllByContinent = page.getContent();
//        List<Sighting> publicList = new ArrayList<>();
//        for (Sighting s : showAllByContinent) {
//            if (!s.getKeepPrivate()) {
//                    publicList.add(s);
//                }
//            }
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");
//        model.addAttribute("continent", continentString);
//        model.addAttribute("sighting", new Sighting());
//        model.addAttribute("sightings", publicList);
//        return sightingService.findPaginated(continent, 1, pageSize, sortField, sortDir);
//    }


  // returns all sightings from a specific country
  @GetMapping("/country/{country}")
  public String getAllByCountry(Model model, @PathVariable("country") String country,
      Sighting sighting) {
    List<Sighting> showAllByCountry = sightingService.getAllByCountry(country);
    List<Sighting> publicList = new ArrayList<>();
    for (Sighting s : showAllByCountry) {
      if (!s.getKeepPrivate()) {
        publicList.add(s);
      }
    }
    model.addAttribute("country", sighting.getCountry());
    model.addAttribute("sightings", publicList);
    return "country";
  }

  // returns all sightings by current user
  @GetMapping("/yourSightings")
  public String getAllByCurrentUser(Authentication authentication, Model model) {
    User currentUser = userService.findUserByEmail(authentication.getName());
    Optional user = userService.getUserByUserName(currentUser.getUserName());
    List<Sighting> showAllByCurrentUser = sightingService.getAllByUser(user);
    model.addAttribute("sightings", showAllByCurrentUser);
    for (Sighting sighting : showAllByCurrentUser) {
      model.addAttribute("sighting", sighting);
    }
    return "yourSightings";
  }

  // returns page with detailed information about a specific sighting
  @GetMapping("/sightingPage/{sightingId}")
  public String getSpecificSighting(@PathVariable("sightingId") Long sightingId, Model model,
      Authentication authentication) {
    Sighting sighting = sightingService.getSightingById(sightingId);
    hideLocation(sighting, sighting.getLocationHidden(), authentication);
    model.addAttribute("sighting", sighting);
    Species species = new Species(sighting.getSpeciesName());
    model.addAttribute("species", species);
    model.addAttribute("name", species.getName());
    model.addAttribute("dateOfSighting", sighting.getDateOfSighting());
    model.addAttribute("user", sighting.getUser());
    if (!sighting.getPhotos().isEmpty()) {
      List<Photo> photos = sighting.getPhotos();
      model.addAttribute("photos", photos);
      for (Photo photo : photos) {
        model.addAttribute("photo", photo.getFileName());
      }
    } else {
      String placeholder = "placeholder.jpg";
      Photo photo = new Photo();
      photo.setFileName(placeholder);
      sighting.getPhotos().add(photo);
    }
    return "sightingPage";
  }

  private void hideLocation(Sighting sighting, Boolean locationHidden,
      Authentication authentication) {
    User user = sighting.getUser();
    if (authentication == null && locationHidden == true) {
      sighting.setLocation("Location is hidden.");
    } else if (locationHidden == true && !authentication.getName().equals(user.getEmail())) {
      sighting.setLocation("Location is hidden.");
    } else {
      sighting.setLocation(sighting.getLocation());
    }
  }

  // handler to get page to allow editing of sighting
  //TODO - figure out problem with speciesname autocomplete on species edit page
  @GetMapping("/updateSighting/{sightingId}")
  public String getUpdateSightingPage(@PathVariable("sightingId") Long sightingId, Model model) {
    Sighting sighting = sightingService.getSightingById(sightingId);
    model.addAttribute("sighting", sighting);
    model.addAttribute("countryList", sightingService.getCountryList());
    return "updateSighting";
  }

  @PostMapping("/updateSighting/save")
  public String updateSighting(@Valid @ModelAttribute("sighting") Sighting sighting,
      @RequestParam MultipartFile imageFile, Model model) throws IOException {
    String speciesName = sighting.getSpeciesName();
    model.addAttribute("speciesName", speciesName);
    sightingService.updateSighting(sighting);
    Photo photo = new Photo();
    if (!imageFile.isEmpty()) {
      photo.setFileName(imageFile.getOriginalFilename());
      photo.setPath(photo.getPath());
      photo.setSighting(sightingService.getSightingById(sighting.getSightingId()));
      photo.setUser(sightingService.getSightingById(sighting.getSightingId()).getUser());
      sightingService.saveImage(imageFile, photo);
      model.addAttribute("photo", photo);
      model.addAttribute("sighting", sighting);
    }
    return "redirect:/yourSightings";
  }

  // deletes sighting
  @PostMapping("/{sightingId}/deleteSighting")
  public String deleteSighting(@ModelAttribute Sighting sighting,
      @PathVariable("sightingId") Long sightingId, Authentication authentication, Model model) {
    User currentUser = userService.findUserByEmail(authentication.getName());
    model.addAttribute("user", currentUser);
    model.addAttribute("sighting", sightingService.getSightingById(sightingId));
    model.addAttribute("sightingId", sightingId);
    sightingService.deleteSighting(sightingId);
    return "redirect:/yourSightings";
  }

  /******************* HANDLER METHODS FOR HTML **********************/

  // handler method to handle home page request
//    @GetMapping(path = {"/index", "/"})
//    public String index(Model model){
//        List<Sighting> sightings = sightingService.getAllSightings();
//        return findPaginated(1, "dateOfSighting",  "desc", model);
//    }

  // handler to allow pagination
  @GetMapping("/index/{pageNumber}")
  public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
      @RequestParam("sortField") String sortField,
      @RequestParam("sortDir") String sortDir,
      Model model) {
    int pageSize = 10;
    Page<Sighting> page = sightingService.findPaginated(pageNumber, pageSize, sortField, sortDir);
    List<Sighting> sightings = page.getContent();
    List<Sighting> publicList = new ArrayList<>();
    for (Sighting s : sightings) {
      if (!s.getKeepPrivate()) {
        model.addAttribute("name", s.getUser().getUserName());
        publicList.add(s);
      }
    }
    List<Photo> allPhotos = photoService.getAllPhotos();
    Photo photo1 = photoService.getRandomImage1();
    Photo photo2 = photoService.getRandomImage2();
    Photo photo3 = photoService.getRandomImage3();
    Photo photo = new Photo();
    if (photo.equals(photo1)) {
      model.addAttribute("sightingId", photo1.getSighting().getSightingId());
      model.addAttribute("speciesName", photo1.getSighting().getSpeciesName());
    } else if (photo.equals(photo2)) {
      model.addAttribute("sightingId", photo2.getSighting().getSightingId());
      model.addAttribute("speciesName", photo2.getSighting().getSpeciesName());
    } else if (photo.equals(photo3)) {
      model.addAttribute("sightingId", photo3.getSighting().getSightingId());
      model.addAttribute("speciesName", photo3.getSighting().getSpeciesName());
    }
    model.addAttribute("photos", allPhotos);
    model.addAttribute("photo1", photo1);
    model.addAttribute("photo2", photo2);
    model.addAttribute("photo3", photo3);
    model.addAttribute("sightings", publicList);
    model.addAttribute("sighting", new Sighting());
    model.addAttribute("currentPage", pageNumber);
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("desc") ? "asc" : "desc");
    return "index";
  }

  @GetMapping("/sortBy")
  public String sortByButton(Model model, Sighting sighting) {
    List<Sighting> sightingsDescending = sightingService.getAllSightings();
    List<Sighting> sightingsAscending = sightingService.getAllSightingsOldestToNewest();
    model.addAttribute("sighting", sighting);
    model.addAttribute("descending", sightingsDescending);
    model.addAttribute("ascending", sightingsAscending);
    return "sortBy";
  }

  // handler method to handle search field request
  @RequestMapping(path = {"/", "/index", "/search"})
  public String home(Model model, Sighting sighting, @Param("species") String speciesName) {
    if (speciesName != null) {
      List<Sighting> sightings = sightingService.searchBySpecies(speciesName);
      List<Sighting> publicList = new ArrayList<>();
      for (Sighting s : sightings) {
        if (!s.getKeepPrivate()) {
          publicList.add(s);
        }
      }
      model.addAttribute("species", speciesName);
      model.addAttribute("sighting", sighting);
      model.addAttribute("sightings", publicList);
      return "species";
    } else {
      List<Sighting> sightings = sightingService.getAllSightings();
      model.addAttribute("sighting", sighting);
      model.addAttribute("sightings", sightings);
      return findPaginated(1, "dateOfSighting", "desc", model);
    }
  }

  // returns the call to api ITIS database for form autocomplete
  @GetMapping("/speciesNameAutocomplete")
  @ResponseBody
  public List<String> speciesNameAutocomplete(
      @RequestParam(value = "term", required = false, defaultValue = "") String term) {
    List<String> suggestions = sightingService.getSearchedSpeciesNames(term);
    return suggestions;
  }

  // Converts empty strings to null when addSighting form is submitted to prevent IllegalArgumentException when
  // enum-dropdowns are left blank.
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

}



