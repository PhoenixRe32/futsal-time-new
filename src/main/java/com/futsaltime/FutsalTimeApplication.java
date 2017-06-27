package com.futsaltime;

import com.futsaltime.database.models.Facility;
import com.futsaltime.database.repositories.IFacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class FutsalTimeApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//
//		URL[] urls = ((URLClassLoader)cl).getURLs();
//
//		for(URL url: urls){
//			System.out.println(url.getFile());
//		}
		SpringApplication.run(FutsalTimeApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IFacilityRepository repository) {
		return (String... args) -> {
			// save a couple of customers
			repository.save(new Facility("Jack"));
			repository.save(new Facility("Chloe"));
			repository.save(new Facility("Kim"));
			repository.save(new Facility("David"));
			repository.save(new Facility("Michelle"));

			// fetch all Facilitys
			log.info("Facilitys found with findAll():");
			log.info("-------------------------------");
			for (Facility facility : repository.findAll()) {
				log.info(facility.toString());
			}
			log.info("");

			// fetch an individual Facility by ID
			Optional<Facility> facility = repository.findById(1L);
			log.info("Facility found with findById(1L):");
			log.info("--------------------------------");
			log.info(facility.toString());
			log.info("");

			// fetch Facilitys by last name
			log.info("Facility found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Facility bfacility : repository.findAllByFacilityName("Bauer")) {
				log.info(bfacility.toString());
			}
			log.info("");
		};
	}
}
