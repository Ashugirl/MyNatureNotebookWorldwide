package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SightingService {
   SightingRepository sightingRepository;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public SightingService(SightingRepository sightingRepository, UserServiceImpl userServiceImpl) {
        this.sightingRepository = sightingRepository;
        this.userServiceImpl = userServiceImpl;
        System.out.println("Hello from the sightingservice constructor");
    }

    public Sighting createSighting(Sighting sighting){
        return sightingRepository.save(sighting);
    }

    public Sighting getSightingById(Long sightingId){
        return sightingRepository.findById(sightingId).get();
    }

    public List<Sighting> getAllSightings(){
        return sightingRepository.findAll();
    }

    public List<Sighting> getAllByUser(User user){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByUser(user.getId())
              .forEach(sightings::add);
        return sightings;
    }

    public List<Sighting> getAllBySpeciesCommonName(String commonName){
        List<Sighting> sightings = new ArrayList<>();
         sightingRepository.findAllBySpeciesCommonName(commonName)
                 .forEach(sightings::add);
                  return sightings;
   }

    public List<Sighting> getAllBySpeciesScientificName(String scientificName){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllBySpeciesScientificName(scientificName)
                .forEach(sightings::add);
        return sightings;
    }

    public List<Sighting> getAllByContinent(Sighting.Continent continent){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByContinent(continent)
                .forEach(sightings::add);
        return sightings;
    }
    public List<Sighting> getAllByCountry(Locale country){
        List<Sighting> sightings = new ArrayList<>();
        sightingRepository.findAllByCountry(country)
                .forEach(sightings::add);
        return sightings;
    }
    public void editSighting(Sighting sighting) {
        sightingRepository.findById(sighting.getSightingId())
                .ifPresent(updatedSighting -> {
                    updatedSighting.setSpeciesCommonName(sighting.getSpeciesCommonName());
                    updatedSighting.setSpeciesScientificName(sighting.getSpeciesScientificName());
                    updatedSighting.setTimeOfSighting(sighting.getTimeOfSighting());
                    updatedSighting.setContinent(sighting.getContinent());
                    updatedSighting.setCountry(sighting.getCountry());
                    updatedSighting.setLocation(sighting.getLocation());
                    updatedSighting.setLifer(sighting.getLifer());
                    updatedSighting.setLocationHidden(sighting.isLocationHidden());
                    updatedSighting.setKeepPrivate(sighting.isKeepPrivate());
                    updatedSighting.setQuantity(sighting.getQuantity());
                    sightingRepository.save(updatedSighting);
                });
    }

    public void deleteSighting(Long sightingId){
        sightingRepository.deleteById(sightingId);
    }

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

    public static List<String> getSearchedCommonNames(String query){
        Map<String, String> speciesMap = new HashMap<>();
        //List<String> commonNames = new ArrayList<>();
        //List<String> scientificNames = new ArrayList<>();
        try {
            System.out.println("What species would you like to see?");
            String baseUrl = "https://www.itis.gov/ITISWebService/jsonservice/getITISTermsFromCommonName?srchKey=";
            Scanner inputScanner = new Scanner(System.in);
            query = inputScanner.nextLine();
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
                        System.out.println(itisObject.keySet());
                        //scientificNames.add(i, itisObject.get("scientificName").toString());
                        //System.out.println(itisObject.get("commonNames").toString());
                        //.out.println(itisObject.get("scientificName"));
                        //commonNames.add(i, itisObject.get("commonNames").toString());
                        speciesMap.put("scientificNames", "commonNames");

                    }
                    for(String scientificName : speciesMap.keySet()){
                        System.out.println(scientificName);
                        System.out.println(speciesMap.values());
                    }
//                    for(String scientificName : scientificNames){
//                        System.out.println(scientificName);}
//                    for(String commonName : commonNames){
//                        System.out.println(commonName);
//                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(commonNames);
        return (List<String>) speciesMap;
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
