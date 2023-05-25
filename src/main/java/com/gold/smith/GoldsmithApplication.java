package com.gold.smith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GoldsmithApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldsmithApplication.class, args);
	}

}



/*@SpringBootApplication
public class GoldsmithApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GoldsmithApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GoldsmithApplication.class);
	}
}*/
