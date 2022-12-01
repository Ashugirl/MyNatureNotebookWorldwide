package be.avivaCode.MyNatureNotebookWorldwide.service;

import be.avivaCode.MyNatureNotebookWorldwide.data.Photo;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        String placeHolderFileName = "IMG_0214.jpeg";
        System.out.println("current path " +currentPath);
        Path absolutePath = currentPath.toAbsolutePath();
        Path path = null;
        System.out.println("absolute path " + absolutePath);
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        System.out.println("imagefile filename " + imageFile.getOriginalFilename());
        byte[] bytes = imageFile.getBytes();
        path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        System.out.println("Path at end of method " + path);
        Files.write(path, bytes);
    }

}
