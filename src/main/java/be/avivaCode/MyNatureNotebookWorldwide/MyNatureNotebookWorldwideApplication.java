package be.avivaCode.MyNatureNotebookWorldwide;


import be.avivaCode.MyNatureNotebookWorldwide.security.PasswordEncoder;
import be.avivaCode.MyNatureNotebookWorldwide.service.SightingService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
@EnableCaching
public class MyNatureNotebookWorldwideApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNatureNotebookWorldwideApplication.class, args);





	}
	}







