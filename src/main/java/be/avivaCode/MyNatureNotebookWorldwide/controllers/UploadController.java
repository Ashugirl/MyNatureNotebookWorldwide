package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {
//    public static String UPLOAD_DIRECTORY = "C:/Users/Aviva/uploads/";
//
//    @GetMapping("/addSighting/imageUpload")
//    public String displayUploadForm(){
//        return "/addSighting";
//    }
//
//    @PostMapping("/upload")
//    public String uploadImage(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException{
//
//        if(file.isEmpty()){
//            attributes.addFlashAttribute("message", "Please select an image to upload.");
//            return "redirect:/addSighting";
//        }
//
//        // normalize the file path
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        // save the file to local file system
//        try{
//            Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        // return success response
//        attributes.addFlashAttribute("message", "You have successfully uploaded " + fileName);
//
//        return "redirect:/addSighting";





//        StringBuilder fileNames = new StringBuilder();
//        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes());
//        model.addAttribute("msg", "Upload images: " + fileNames.toString());
//        return "imageUpload/addSighting";
    }


