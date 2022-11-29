package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.PhotoRepository;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SightingService {
    private SightingRepository sightingRepository;
    private UserService userService;
    private PhotoRepository photoRepository;
    private PhotoService photoService;

    @Autowired
    public SightingService(SightingRepository sightingRepository, UserService userService, PhotoRepository photoRepository, PhotoService photoService) {
        this.sightingRepository = sightingRepository;
        this.userService = userService;
        this.photoRepository = photoRepository;
        this.photoService = photoService;

    }

    public SightingService() {

    }

    public Sighting createSighting(Sighting sighting){
        return sightingRepository.save(sighting);
    }

    public Sighting getSightingById(Long sightingId){
        System.out.println(sightingRepository.findById(sightingId).get());
        return sightingRepository.findById(sightingId).get();
    }

    //gets all sigthing from newest to oldest
    public List<Sighting> getAllSightings(){
        List<Sighting> allSightings = sightingRepository.findAll();
        allSightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return allSightings;
    }
    @Cacheable("speciesNames")
    public List<Sighting> searchBySpecies(String speciesName){
        if(speciesName != null){
            System.out.println("service speciesName" + speciesName);
            System.out.println("service return value " + sightingRepository.searchBySpecies(speciesName));
            return sightingRepository.searchBySpecies(speciesName);
        }
        return sightingRepository.findAll();
    }

    // returns all sightings by user from newest to oldest
    public List<Sighting> getAllByUser(Optional<User> user){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByUser(user)
              .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

//    // returns all sightings of a species from newest to oldest
//    public List<Sighting> getAllBySpeciesName(String speciesName){
//        List<Sighting> sightings = new ArrayList<>();
//         sightingRepository.findAllBySpeciesName(speciesName)
//                 .forEach(sightings::add);
//        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
//        return sightings;
//    }

    // returns all sightings from a continent from newest to oldest
    public List<Sighting> getAllByContinent(Sighting.Continent continent){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByContinent(continent)
                .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings of a species from newest to oldest
    public List<Sighting> getAllBySpeciesName(String speciesName){
        List<Sighting> sightings = new ArrayList<>();
        System.out.println("passing service " + speciesName);
        System.out.println("Check if repo works " + sightingRepository.findAllBySpeciesName(speciesName));
        sightingRepository.findAllBySpeciesName(speciesName)
                .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings from a country from newest to oldest
    public List<Sighting> getAllByCountry(String country){
        List<Sighting> sightings = new ArrayList<>();
        System.out.println("passing service " + country);
        System.out.println("check if repo works " +sightingRepository.findAllByCountry(country));
                sightingRepository.findAllByCountry(country)
                        .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    public void updateSighting(Long sightingId) {
        Sighting sighting = sightingRepository.getReferenceById(sightingId);
//        sightingRepository.findById(sightingId)
//                .ifPresent(updatedSighting -> {
//                    updatedSighting.setSightingId(updatedSighting.getSightingId());
//                    updatedSighting.setSpeciesName(updatedSighting.getSpeciesName());
//                    updatedSighting.setDateOfSighting(updatedSighting.getDateOfSighting());
//                    updatedSighting.setTimeOfSighting(updatedSighting.getTimeOfSighting());
//                    updatedSighting.setContinent(updatedSighting.getContinent());
//                    updatedSighting.setCountry(updatedSighting.getCountry());
//                    updatedSighting.setLocation(updatedSighting.getLocation());
//                    updatedSighting.setLifer(updatedSighting.getLifer());
//                    updatedSighting.setLocationHidden(updatedSighting.getLocationHidden());
//                    updatedSighting.setKeepPrivate(updatedSighting.getKeepPrivate());
//                    updatedSighting.setQuantity(updatedSighting.getQuantity());
//                    sightingRepository.save(updatedSighting);
//                });
                    sightingRepository.save(sighting);
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }


//
//    public void hideLocation(Boolean locationHidden){
//        if(sighting.getLocationHidden() == true){
//            sighting.setLocation("Location hidden");
//        }
//    }
    public void deleteSighting(Long sightingId){
        sightingRepository.deleteById(sightingId);
    }

    // gets a list of countries from for dropdown list on form
    public List<String> getCountryList() {
        String[] countryCodes = Locale.getISOCountries();
        List<String> countryList;
        Map<String, String> countryListMap = new TreeMap<>();
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();
            countryListMap.put(code, name);
        }
        countryList = new ArrayList<>(countryListMap.values());
        Collections.sort(countryList);
        return countryList;
    }

    //GETTING INFO FROM ITIS API
    @Cacheable("searchedNames")
    public List<String> getSearchedSpeciesNames(String query){
        Map<String, String> speciesMap = new HashMap<>();
        List<String> speciesList = new ArrayList<>();
        try {
            //System.out.println("Enter a species");
            String baseUrl = "https://www.itis.gov/ITISWebService/jsonservice/getITISTermsFromCommonName?srchKey=";
            //trims and appends user query to URL
            String encodeQuery = encodeValue(query.trim());
            String completeURL = baseUrl + encodeQuery;
            URL getItisFromCommonName = new URL(completeURL);
            //gets request from API
            HttpURLConnection conn = (HttpURLConnection) getItisFromCommonName.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Check if connect is made
            int responseCode = conn.getResponseCode();
            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(getItisFromCommonName.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                // Gets commonNames and scientificName from commonName search
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));
                JSONArray itisTerms = (JSONArray) dataObject.get("itisTerms");
                JSONObject itisObject;
                if (itisTerms.listIterator().hasNext()) {
                    for (int i = 0; i < itisTerms.size(); i++) {
                        itisObject = (JSONObject) itisTerms.get(i);
                        //System.out.println(itisObject.keySet());
                        //scientificNames.add(i, itisObject.get("scientificName").toString());
                        //System.out.println(itisObject.get("commonNames").toString());
                        //.out.println(itisObject.get("scientificName"));
                        //commonNames.add(i, itisObject.get("commonNames").toString());
                        try {
                            speciesMap.put(itisObject.get("scientificName").toString(),
                                    itisObject.get("commonNames").toString());
                        } catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Species not found. Please check spelling.");
                            }
                        speciesList = speciesMap.entrySet()
                                .stream()
                                .map(entry -> entry.getKey() + " - " + entry.getValue().replace("\"", "")
                                        .replace("[", "")

                                        .replace("]", "") )
                                .sorted()
                                .collect(Collectors.toList());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


            return speciesList;
    }

    //encodes search queries with spaces into values that can be appended to URL
    private static String encodeValue(String value){
        try{
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replace("+", "%20");
        } catch (UnsupportedEncodingException ex){
            throw new RuntimeException(ex.getCause());
        }
    }

//public BufferedImage getSightingImage(){
//        BufferedImage sightingImage = new BufferedImage(250, 250, 6);
//    try{
//        Path imgPath = Path.of(" ");
//        Files.createFile(imgPath);
//        sightingImage = ImageIO.read(new URL(""));
//        ImageIO.write(sightingImage, "jpg", new File(String.valueOf(imgPath)));
//    } catch (MalformedURLException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    return sightingImage;
//}


    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException{
        photoService.saveImage(imageFile, photo);
        photoService.savePhoto(photo);
    }
}
