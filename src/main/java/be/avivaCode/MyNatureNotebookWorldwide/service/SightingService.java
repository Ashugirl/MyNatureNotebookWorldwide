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

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        return sightingRepository.findById(sightingId).get();
    }

    //gets all sigthing from newest to oldest
    public List<Sighting> getAllSightings(){
        List<Sighting> allSightings = sightingRepository.findAll();
        allSightings.sort(Comparator.comparing(Sighting::getDateOfSighting).reversed());
        return allSightings;
    }


    public List<Sighting> getAllByUser(Optional<User> user){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByUser(user)
              .forEach(sightings::add);
        return sightings;
    }

    public List<Sighting> getAllBySpeciesName(String speciesName){
        List<Sighting> sightings = new ArrayList<>();
         sightingRepository.findAllBySpeciesName(speciesName)
                 .forEach(sightings::add);
                  return sightings;
   }

    public List<Sighting> getAllByContinent(Sighting.Continent continent){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByContinent(continent)
                .forEach(sightings::add);
        System.out.println(sightings);
        return sightings;
    }
    //TODO THIS WORKS! DON'T TOUCH!
    public List<Sighting> getAllBySpecies(String speciesName){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllBySpeciesName(speciesName)
                .forEach(sightings::add);
        return sightings;
    }
    public List<Sighting> getAllByCountry(Locale country){
        Sighting sighting = new Sighting();
        country = sighting.getCountry();
        System.out.println(country);
        List<Sighting> sightings = new ArrayList<>();
                sightingRepository.findAllByCountry(country)
                        .forEach(sightings::add);
//        sightings.sort(Comparator.comparing(Sighting::getTimeOfSighting).reversed());
        System.out.println(sightings);
        return sightings;
    }
    public void editSighting(Sighting sighting) {
        sightingRepository.findById(sighting.getSightingId())
                .ifPresent(updatedSighting -> {
                    updatedSighting.setSpeciesName(sighting.getSpeciesName());
                    updatedSighting.setDateOfSighting(sighting.getDateOfSighting());
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

    public void deleteSighting(Long sightingId){
        sightingRepository.deleteById(sightingId);
    }

//    public List<Sighting> filterByCountry(Locale country){
//        List<Sighting> sightingsByCountry = sightingRepository.findAllByCountry(country);
//        return sightingsByCountry;
//    }




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
                                .map(entry -> entry.getKey() + " " + entry.getValue())
                                .sorted()
                                .collect(Collectors.toList());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(speciesList);
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







}
