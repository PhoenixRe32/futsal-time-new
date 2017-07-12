package com.futsaltime;

import com.futsaltime.database.models.Bookable;
import com.futsaltime.database.models.Facility;
import com.futsaltime.database.repositories.IFacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
			Facility thoi = new Facility("Thoi");
			Collection<Bookable> thoiFields = Arrays.asList(
					new Bookable(5, thoi),
					new Bookable(5, thoi),
					new Bookable(7, thoi),
					new Bookable(9, thoi));
			thoi.setBookables(thoiFields);
			repository.save(thoi);

			Facility paeek = new Facility("Paeek");
			Collection<Bookable> paeekFields = Arrays.asList(
					new Bookable(5, paeek),
					new Bookable(6, paeek),
					new Bookable(9, paeek),
					new Bookable(10, paeek));
			paeek.setBookables(paeekFields);
			repository.save(paeek);

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
