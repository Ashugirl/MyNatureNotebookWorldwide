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

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getRandomImage1() {
        List<Photo> allPhotos = photoRepository.findAll();
        List<Photo> publicPhotos = new ArrayList<>();
        Photo photo = new Photo();
        for (Photo p : allPhotos) {
            if (!p.getSighting().getKeepPrivate()) {
                publicPhotos.add(p);
            }
        }
        List<Photo> firstThird = new ArrayList<>(publicPhotos.subList(0, publicPhotos.size() / 3));
        int min = 1;
        int max = firstThird.size();
        for (Photo p : firstThird) {
            int randomNumber = (int) (Math.random() * (max - min)) + min;
            photo = firstThird.get(randomNumber);
        }
        return photo;
    }

    public Photo getRandomImage2() {
        List<Photo> allPhotos = photoRepository.findAll();
        List<Photo> publicPhotos = new ArrayList<>();
        Photo photo = new Photo();
        for (Photo p : allPhotos) {
            if (!p.getSighting().getKeepPrivate()) {
                publicPhotos.add(p);
            }
        }
        List<Photo> secondThird= new ArrayList<>(publicPhotos.subList(publicPhotos.size() / 3, (publicPhotos.size()/3)*2));
        int min = 1;
        int max = secondThird.size();
        for (Photo p : secondThird) {
            int randomNumber = (int) (Math.random() * (max - min)) + min;
            photo = secondThird.get(randomNumber);
        }
        return photo;
    }

    public Photo getRandomImage3() {
        List<Photo> allPhotos = photoRepository.findAll();
        List<Photo> publicPhotos = new ArrayList<>();
        Photo photo = new Photo();
        for (Photo p : allPhotos) {
            if (!p.getSighting().getKeepPrivate()) {
                publicPhotos.add(p);
            }
        }
        List<Photo> lastThird = new ArrayList<>(publicPhotos.subList((publicPhotos.size()/3)*2, publicPhotos.size()));
        int min = 1;
        int max = lastThird.size();
        for (Photo p : lastThird) {
            int randomNumber = (int) (Math.random() * (max - min)) + min;
            photo = lastThird.get(randomNumber);
        }
        return photo;
    }
}