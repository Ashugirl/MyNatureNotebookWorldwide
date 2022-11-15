package be.avivaCode.MyNatureNotebookWorldwide.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
public class Sighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sightingId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")//, nullable = false)
    private User user;
    private String speciesCommonName;
    private String speciesScientificName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Continent continent;
    @Column(nullable = false)
    Locale country;
    @Column
    private String location;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    @Column
    LocalDateTime timeOfSighting;
    @Column
    private int quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "classOfAnimal")
    private TaxonomicClass taxonomicClass;
    @Enumerated(EnumType.STRING)
    @Column
    private LifeStage lifeStage;
    @Column
    private boolean deceased;
    @Enumerated(EnumType.STRING)
    @Column
    private Sex sex;
    @Column
    private String behaviour;
    @Column
    private Double plantHeight;
    @Column
    private String notes;
    @Column
    private Boolean lifer;
    private Boolean keepPrivate;
    private Boolean locationHidden;

    public Sighting() {
    }

    public Sighting(User user, String speciesCommonName){
        this.user = user;
        this.speciesCommonName = speciesCommonName;
    }
    public Sighting(User user, String speciesCommonName, String speciesScientificName, Continent continent, Locale country){
        this.user = user;
        this.speciesCommonName = speciesCommonName;
        this.speciesScientificName = speciesScientificName;
        this.continent = continent;
        this.country = country;
    }
    public Sighting(Long sightingId, User user, String speciesCommonName, String speciesScientificName,
                    Continent continent, Locale country, String location, LocalDateTime timeOfSighting,
                    int quantity, TaxonomicClass taxonomicClass, LifeStage lifeStage, Boolean deceased,
                    Sex sex, String behaviour, Double plantHeight, String notes, Boolean lifer,
                    Boolean isPrivate, Boolean isLocationHidden) {
        this.sightingId = sightingId;
        this.user = user;
        this.speciesCommonName = speciesCommonName;
        this.speciesScientificName = speciesScientificName;
        this.continent = continent;
        this.country = country;
        this.location = location;
        this.timeOfSighting = timeOfSighting;
        this.quantity = quantity;
        this.taxonomicClass = taxonomicClass;
        this.lifeStage = lifeStage;
        this.deceased = deceased;
        this.sex = sex;
        this.behaviour = behaviour;
        this.plantHeight = plantHeight;
        this.notes = notes;
        this.lifer = lifer;
        this.keepPrivate = isPrivate;
        this.locationHidden = isLocationHidden;
    }

    public Long getSightingId() {
        return sightingId;
    }

    public void setSightingId(Long sightingId) {
        this.sightingId = sightingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpeciesCommonName() {
        return speciesCommonName;
    }

    public void setSpeciesCommonName(String speciesCommonName) {
        this.speciesCommonName = speciesCommonName;
    }

    public String getSpeciesScientificName() {
        return speciesScientificName;
    }

    public void setSpeciesScientificName(String speciesScientificName) {
        this.speciesScientificName = speciesScientificName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Locale getCountry() {
        return country;
    }

    public void setCountry(Locale country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimeOfSighting() {
        return timeOfSighting;
    }

     public void setTimeOfSighting(LocalDateTime timeOfSighting) {
        this.timeOfSighting = timeOfSighting;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TaxonomicClass getTaxonomicClass() {
        return taxonomicClass;
    }

    public void setTaxonomicClass(TaxonomicClass taxonomicClass) {
        this.taxonomicClass = taxonomicClass;
    }

    public LifeStage getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(LifeStage lifeStage) {
        this.lifeStage = lifeStage;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public Double getPlantHeight() {
        return plantHeight;
    }

    public void setPlantHeight(Double plantHeight) {
        this.plantHeight = plantHeight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getLifer() {
        return lifer;
    }

    public void setLifer(Boolean lifer) {
        this.lifer = lifer;
    }

    public Boolean getKeepPrivate() {
        return keepPrivate;
    }

    public void setKeepPrivate(Boolean keepPrivate) {
        this.keepPrivate = keepPrivate;
    }

    public Boolean getLocationHidden() {
        return locationHidden;
    }

    public void setLocationHidden(Boolean locationHidden) {
        this.locationHidden = locationHidden;
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "sightingId=" + sightingId +
                ", user=" + user +
                ", speciesCommonName='" + speciesCommonName + '\'' +
                ", speciesScientificName='" + speciesScientificName + '\'' +
                ", continent=" + continent +
                ", country=" + country +
                ", location='" + location + '\'' +
                ", timeOfSighting=" + timeOfSighting +
                ", quantity=" + quantity +
                ", taxonomicClass=" + taxonomicClass +
                ", lifeStage=" + lifeStage +
                ", isDeceased=" + deceased +
                ", sex=" + sex +
                ", behaviour='" + behaviour + '\'' +
                ", plantHeight=" + plantHeight +
                ", notes='" + notes + '\'' +
                ", isLifer=" + lifer +
                ", isPrivate=" + keepPrivate +
                ", isLocationHidden=" + locationHidden +
                '}';
    }

    public enum Continent {
        AFRICA("Africa"),
        ANTARCTICA("Antartica"),
        ASIA("Asia"),
        EUROPE("Europe"),
        NORTH_AMERICA("North America"),
        OCEANIA("Oceania"),
        SOUTH_AMERICA("South America");

        private final String displayValue;
        private Continent(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

    public enum LifeStage{
        NEONATAL("Neonatal"),
        JUVENILE("Juvenile"),
        ADULT("Adult");

        private final String displayValue;

        private LifeStage(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

    public enum TaxonomicClass{
        MAMMAL("Mammal"),
        BIRD("Bird"),
        REPTILE("Reptile"),
        AMPHIBIAN("Amphibian"),
        FISH("Fish"),
        INVERTEBRATE("Invertebrate");

        private final String displayValue;

        private TaxonomicClass(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

    public enum Sex {
        MALE("Male"),
        FEMALE("Female"),
        UNKNOWN("Unknown");

        private final String displayValue;

        private Sex(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }


}
