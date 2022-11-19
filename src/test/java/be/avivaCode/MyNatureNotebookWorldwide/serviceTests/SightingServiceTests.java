package be.avivaCode.MyNatureNotebookWorldwide.serviceTests;
import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserService;
import be.avivaCode.MyNatureNotebookWorldwide.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class SightingServiceTests {
    private static User user1;
    private static User user2;
    private static Sighting sighting1;
    private static Sighting sighting2;
    private static Sighting sighting3;
    private static Sighting sighting4;

    @Mock
    private SightingRepository sightingRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();
    @InjectMocks
    private SightingService sightingService = new SightingService(sightingRepository, userService);


    @BeforeClass
    public static void createData(){
        user1 = new User();
        user2 = new User();
        sighting1 = new Sighting(user1, "Common swift - Apus apus", Sighting.Continent.AFRICA, new Locale("en", "Egypt"));
        sighting2 = new Sighting(user1, "Red fox Vulpes vulpes", Sighting.Continent.EUROPE, new Locale("en", "Belgium"));
        sighting3 = new Sighting(user2, "Common swift Apus apus", Sighting.Continent.EUROPE, new Locale("en", "Germany"));
        sighting4 = new Sighting(user2, "Cattle egret Bubulcus ibis", Sighting.Continent.AFRICA, new Locale("en", "Botswana"));


    }
//
//    @Test
//    public void testIfYouCanGetAllByUser(){
//        List<Sighting> sightingsByUser1 = new ArrayList<>();
//        sightingsByUser1.add(sighting1);
//        sightingsByUser1.add(sighting2);
//        when(sightingRepository.findAllByUser(user1.getId())).thenReturn(sightingsByUser1);
//        List<Sighting> findAllByUser1 = sightingService.getAllByUser(user1);
//        Assert.assertEquals(2, findAllByUser1.size());
//        Assert.assertTrue(findAllByUser1.contains(sighting1));
//        Assert.assertTrue(findAllByUser1.contains(sighting2));
//        Assert.assertFalse(findAllByUser1.contains(sighting3));
//        Assert.assertFalse(findAllByUser1.contains(sighting4));
//    }
    @Test
    public void testIfYouCanGetAllByContinent(){
        List<Sighting> sightingsFromAfrica = new ArrayList<>();
        sightingsFromAfrica.add(sighting1);
        sightingsFromAfrica.add(sighting4);
        when(sightingRepository.findAllByContinent(Sighting.Continent.AFRICA)).thenReturn((sightingsFromAfrica));
        List<Sighting> findAllFromAfrica = sightingService.getAllByContinent(Sighting.Continent.AFRICA);
        Assert.assertEquals(2, findAllFromAfrica.size());
        Assert.assertTrue(findAllFromAfrica.contains(sighting1));
        Assert.assertTrue(findAllFromAfrica.contains(sighting4));
        Assert.assertFalse(findAllFromAfrica.contains(sighting3));
        Assert.assertFalse(findAllFromAfrica.contains(sighting2));
    }
    @Test
    public void testIfYouCanGetAllByCommonName(){
        List<Sighting> sightingsOfSwifts = new ArrayList<>();
        sightingsOfSwifts.add(sighting1);
        sightingsOfSwifts.add(sighting3);
        when(sightingRepository.findAllBySpeciesName("Common swift Apus Apus")).thenReturn((sightingsOfSwifts));
        List<Sighting> findAllOfSwifts = sightingService.getAllBySpeciesName("Common swift Apus Apus");
        Assert.assertEquals(2, findAllOfSwifts.size());
        Assert.assertTrue(findAllOfSwifts.contains(sighting1));
        Assert.assertTrue(findAllOfSwifts.contains(sighting3));
        Assert.assertFalse(findAllOfSwifts.contains(sighting4));
        Assert.assertFalse(findAllOfSwifts.contains(sighting2));
    }
    @Test
    public void testIfYouCanGetAllByScientificName(){
        List<Sighting> sightingsOfVulpes = new ArrayList<>();
        sightingsOfVulpes.add(sighting2);
        when(sightingRepository.findAllBySpeciesName("Vulpes vulpes")).thenReturn((sightingsOfVulpes));
        List<Sighting> findAllOfVulpes = sightingService.getAllBySpeciesName("Vulpes vulpes");
        Assert.assertEquals(1, findAllOfVulpes.size());
        Assert.assertTrue(findAllOfVulpes.contains(sighting2));
        Assert.assertFalse(findAllOfVulpes.contains(sighting4));
        Assert.assertFalse(findAllOfVulpes.contains(sighting3));
        Assert.assertFalse(findAllOfVulpes.contains(sighting1));
    }

//    @Test
//    public void testIfYouCanGetAllByCountry(){
//        List<Sighting> sightingsFromBelgium = new ArrayList<>();
//        sightingsFromBelgium.add(sighting2);
//        when(sightingRepository.findAllByCountry(new Locale("en", "Belgium"))).thenReturn((sightingsFromBelgium));
//        List<Sighting> findAllFromBelgium = sightingService.getAllByCountry(new Locale("en", "Belgium"));
//        Assert.assertEquals(1, findAllFromBelgium.size());
//        Assert.assertTrue(findAllFromBelgium.contains(sighting2));
//        Assert.assertFalse(findAllFromBelgium.contains(sighting4));
//        Assert.assertFalse(findAllFromBelgium.contains(sighting3));
//        Assert.assertFalse(findAllFromBelgium.contains(sighting1));
//    }
    @Test
    public void testIfYouCanGetOneById(){
        Optional<Sighting> sighting = Optional.ofNullable(sighting1);
        Long sightingId = sighting.get().getSightingId();
        when(sightingRepository.findById(sightingId)).thenReturn(Optional.ofNullable(sighting1));
        Sighting sightingFound = sightingService.getSightingById(sightingId);
        Assert.assertEquals(sighting1, sightingFound);
        Assert.assertNotEquals(sighting2, sightingFound);
    }

}
