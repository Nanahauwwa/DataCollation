package io.nana.datacollation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DataCollationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCollationApplication.class, args);
	}

}
