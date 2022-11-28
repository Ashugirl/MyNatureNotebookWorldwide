package be.avivaCode.MyNatureNotebookWorldwide.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Sighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sightingId;
    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String speciesName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Continent continent;
    @Column(nullable = false)
    String country;
    @Column
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    LocalDate dateOfSighting= LocalDate.now();
    @DateTimeFormat(pattern = "HH:mm")
    @Column
    LocalTime timeOfSighting= LocalTime.of(00,00);
    @Column
    private int quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "classOfAnimal", nullable = true)
    private TaxonomicClass taxonomicClass;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private LifeStage lifeStage;
    @Column
    private Boolean deceased;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Sex sex;
    @Column
    private String behaviour;
    @Column
    private String notes;
    @Column
    private Boolean lifer = false;
    private Boolean keepPrivate = false;
    private Boolean locationHidden = false;

    @OneToMany(mappedBy = "sighting")
    private List<Photo> photos;



    public Sighting() {
    }

    public Sighting(User user, String speciesName){
        this.user = user;
        this.speciesName = speciesName;
    }
    public Sighting(User user, String speciesName,  Continent continent, String country){
        this.user = user;
        this.speciesName = speciesName;
        this.continent = continent;
        this.country = country;
    }
    public Sighting(Long sightingId, User user, String speciesName,
                    Continent continent, String country, String location, LocalDate dateOfSighting, LocalTime timeOfSighting,
                    int quantity, TaxonomicClass taxonomicClass, LifeStage lifeStage, Boolean deceased,
                    Sex sex, String behaviour, String notes, Boolean lifer,
                    Boolean isPrivate, Boolean isLocationHidden) {
        this.sightingId = sightingId;
        this.user = user;
        this.speciesName = speciesName;
        this.continent = continent;
        this.country = country;
        this.location = location;
        this.dateOfSighting = dateOfSighting;
        this.timeOfSighting = timeOfSighting;
        this.quantity = quantity;
        this.taxonomicClass = taxonomicClass;
        this.lifeStage = lifeStage;
        this.deceased = deceased;
        this.sex = sex;
        this.behaviour = behaviour;
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

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateOfSighting() {
        return dateOfSighting;
    }

    public void setDateOfSighting(LocalDate dateOfSighting) {
        this.dateOfSighting = dateOfSighting;
    }

    public LocalTime getTimeOfSighting() {
        return timeOfSighting;
    }

    public void setTimeOfSighting(LocalTime timeOfSighting) {
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

    public Boolean getDeceased() {
        return deceased;
    }

    public void setDeceased(Boolean deceased) {
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "sightingId=" + sightingId +
                ", user=" + user +
                ", species name='" + speciesName + '\'' +
                ", continent=" + continent +
                ", country=" + country +
                ", location='" + location + '\'' +
                ", dateOfSighting=" + dateOfSighting +
                ", quantity=" + quantity +
                ", taxonomicClass=" + taxonomicClass +
                ", lifeStage=" + lifeStage +
                ", isDeceased=" + deceased +
                ", sex=" + sex +
                ", behaviour='" + behaviour + '\'' +
                ", notes='" + notes + '\'' +
                ", isLifer=" + lifer +
                ", isPrivate=" + keepPrivate +
                ", isLocationHidden=" + locationHidden +
                '}';
    }

    public enum Continent {
        AFRICA("Africa"),
        ANTARCTICA("Antarctica"),
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
        INVERTEBRATE("Invertebrate"),
        EMPTY("");

        @Override
        public String toString() {
            return this == EMPTY ? "" : this.name();
        }

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
