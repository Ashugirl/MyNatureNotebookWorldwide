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
import java.util.ArrayList;
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
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path= Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    public  List<Photo> getAllPhotos(){
       return  photoRepository.findAll();
    }

    public Photo getRandomImage(){
        List<Photo> allPhotos = photoRepository.findAll();
        List<Photo> publicPhotos = new ArrayList<>();
        for (Photo p : allPhotos) {
            if (!p.getSighting().getKeepPrivate()) {
                publicPhotos.add(p);
            }
        }
        int min = 1;
        int max = publicPhotos.size();
        int randomNumber = (int) (Math.random()*(max-min)) + min;
        Photo photo = publicPhotos.get(randomNumber);
        return photo;

    }

}
