package se.ifmo.ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class IadApplication {

	public static void main(String[] args) {
		SpringApplication.run(IadApplication.class, args);
	}

}

