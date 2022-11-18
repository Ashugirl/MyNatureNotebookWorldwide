package be.avivaCode.MyNatureNotebookWorldwide;


import be.avivaCode.MyNatureNotebookWorldwide.security.PasswordEncoder;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class MyNatureNotebookWorldwideApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNatureNotebookWorldwideApplication.class, args);



		//SightingService.getSearchedCommonNames("");

	}






}
