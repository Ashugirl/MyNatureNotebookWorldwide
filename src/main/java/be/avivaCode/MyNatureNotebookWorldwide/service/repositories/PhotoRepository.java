package be.avivaCode.MyNatureNotebookWorldwide.service.repositories;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository <Photo, Long> {

}
