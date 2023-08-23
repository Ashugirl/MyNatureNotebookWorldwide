package be.avivaCode.MyNatureNotebookWorldwide.data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long photoId;
  private String path;
  private String fileName;
  private LocalDateTime sightingDate;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "sighting_sighting_id")
  private Sighting sighting;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  public Photo() {
  }

  public Sighting getSighting() {
    return sighting;
  }

  public void setSighting(Sighting sighting) {
    this.sighting = sighting;
  }

  public Long getPhotoId() {
    return photoId;
  }

  public void setPhotoId(Long photoId) {
    this.photoId = photoId;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public LocalDateTime getSightingDate() {
    Sighting sighting = getSighting();
    sightingDate = sighting.getDateOfSighting();
    return sightingDate;
  }

  public void setSightingDate(LocalDateTime sightingDate) {
    this.sightingDate = sightingDate;
  }
}
