package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SightingService {
   SightingRepository sightingRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public SightingService(SightingRepository sightingRepository, UserServiceImpl userServiceImpl) {
        this.sightingRepository = sightingRepository;
        this.userServiceImpl = userServiceImpl;

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

    // returns all sightings by user from newest to oldest
    public List<Sighting> getAllByUser(Optional<User> user){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByUser(user)
              .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings of a species from newest to oldest
    public List<Sighting> getAllBySpeciesName(String speciesName){
        List<Sighting> sightings = new ArrayList<>();
         sightingRepository.findAllBySpeciesName(speciesName)
                 .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings from a continent from newest to oldest
    public List<Sighting> getAllByContinent(Sighting.Continent continent){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByContinent(continent)
                .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings of a species from newest to oldest
    public List<Sighting> getAllBySpecies(String speciesName){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllBySpeciesName(speciesName)
                .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    // returns all sightings from a country from newest to oldest
    public List<Sighting> getAllByCountry(String country){
        List<Sighting> sightings = new ArrayList<>();
                sightingRepository.findAllByCountry(country)
                        .forEach(sightings::add);
        sightings.sort(Comparator.comparing(Sighting::getDateOfSighting).thenComparing(Sighting::getTimeOfSighting).reversed());
        return sightings;
    }

    public void editSighting(Sighting sighting) {
        sightingRepository.findById(sighting.getSightingId())
                .ifPresent(updatedSighting -> {
                    updatedSighting.setSpeciesName(sighting.getSpeciesName());
                    updatedSighting.setDateOfSighting(sighting.getDateOfSighting());
                    updatedSighting.setTimeOfSighting(sighting.getTimeOfSighting());
                    updatedSighting.setContinent(sighting.getContinent());
                    updatedSighting.setCountry(sighting.getCountry());
                    updatedSighting.setLocation(sighting.getLocation());
                    updatedSighting.setLifer(sighting.getLifer());
                    updatedSighting.setLocationHidden(sighting.getLocationHidden());
                    updatedSighting.setKeepPrivate(sighting.getKeepPrivate());
                    updatedSighting.setQuantity(sighting.getQuantity());
                    sightingRepository.save(updatedSighting);
                });
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
    @Cacheable("strings")
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

                        speciesMap.put(itisObject.get("scientificName").toString(), itisObject.get("commonNames").toString());
                        speciesList = speciesMap.entrySet()
                                .stream()
                                .map(entry -> entry.getKey() + " - " + entry.getValue().replace('"', ' ')
                                        .replace('[', ' ')
                                        .replace(']', ' ') )
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

public BufferedImage getSightingImage(){
        BufferedImage sightingImage = new BufferedImage(250, 250, 6);
    try{
        Path imgPath = Path.of(" ");
        Files.createFile(imgPath);
        sightingImage = ImageIO.read(new URL(""));
        ImageIO.write(sightingImage, "jpg", new File(String.valueOf(imgPath)));
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sightingImage;
}




}
