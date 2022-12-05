package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void savePhoto(Photo photo){
        photoRepository.save(photo);
    }

    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException{
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        System.out.println("absolute path " + absolutePath);
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path= Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    public Optional<Photo> getAPhotoById(Long id){
        return photoRepository.findById(id);
    }

    public List<Photo> getAllPhotos(){
       return  photoRepository.findAll();
    }
}
