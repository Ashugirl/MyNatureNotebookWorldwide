package be.avivaCode.MyNatureNotebookWorldwide.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    public static String UPLOAD_DIRECTORY = "./uploads/";

    @GetMapping("/addSighting")
    public String displayUploadForm(){
        return "/addSighting";
    }

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file, RedirectAttributes attributes) throws IOException{

        if(file.isEmpty()){
            attributes.addFlashAttribute("message", "Please select an image to upload.");
            return "redirect:/addSighting";
        }

//        StringBuilder fileNames = new StringBuilder();
//        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes());
//        model.addAttribute("msg", "Upload images: " + fileNames.toString());
//        return "imageUpload/addSighting";
    }

}
