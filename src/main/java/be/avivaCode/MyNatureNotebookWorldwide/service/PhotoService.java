package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.PhotoRepository;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
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

    public  List<Photo> getAllPhotos(){
       return  photoRepository.findAll();
    }

    public List<Photo> getAllByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Photo> allbyUser = photoRepository.findAllByUser(user.getId());
        return allbyUser;
    }

    public Photo getRandomImage(){
        List<Photo> allPhotos = photoRepository.findAll();
        int min = 1;
        int max = allPhotos.size();
        int randomNumber = (int) (Math.random()*(max-min)) + min;
        Photo photo = allPhotos.get(randomNumber);
        System.out.println("photo id = " + photo.getPhotoId() + " image file = " + photo.getFileName() );
        return photo;

    }
}
