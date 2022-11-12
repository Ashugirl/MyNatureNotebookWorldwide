package be.avivaCode.MyNatureNotebookWorldwide.repositories;

import be.avivaCode.MyNatureNotebookWorldwide.data.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
    List<Sighting> findAll();
    List<Sighting> findAllByUser(Long userId);
    List<Sighting> findAllBySpeciesCommonName(String commonName);
    List<Sighting> findAllBySpeciesScientificName(String scientificName);
    List<Sighting> findAllByContinent(Sighting.Continent continent);
    List<Sighting> findAllByCountry(Locale country);
}
