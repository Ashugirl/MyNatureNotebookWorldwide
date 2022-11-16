package be.avivaCode.MyNatureNotebookWorldwide;

import be.avivaCode.MyNatureNotebookWorldwide.data.User;
import be.avivaCode.MyNatureNotebookWorldwide.repositories.SightingRepository;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MyNatureNotebookWorldwideApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNatureNotebookWorldwideApplication.class, args);
		//SightingService.getSearchedCommonNames("");

	}

}
