package ru.serov.distask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class DistaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistaskApplication.class, args);
	}

}
