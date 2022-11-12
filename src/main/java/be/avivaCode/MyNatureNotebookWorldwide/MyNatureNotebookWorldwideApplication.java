package be.avivaCode.MyNatureNotebookWorldwide;

import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyNatureNotebookWorldwideApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNatureNotebookWorldwideApplication.class, args);


		SightingService.getSearchedCommonNames("");
	}

}
