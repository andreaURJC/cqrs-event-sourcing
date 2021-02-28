package es.urjc.code.ejem1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ejem1Application {

	public static final Logger log = LoggerFactory.getLogger(Ejem1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Ejem1Application.class, args);
	}
}
