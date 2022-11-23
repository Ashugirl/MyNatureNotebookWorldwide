package be.avivaCode.MyNatureNotebookWorldwide.repositories;

import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
    List<Sighting> findAll();
    List<Sighting> findAllByUser(Optional<User> user);
    List<Sighting> findAllBySpeciesName(String speciesName);
    List<Sighting> findAllByContinent(Sighting.Continent continent);
    List<Sighting> findAllByCountry(String country);
    @Query(value = "select * from sighting s where s.speciesName like %:species%", nativeQuery = true)
    List<Sighting> searchBySpecies(@Param("species") String speciesName);

}
