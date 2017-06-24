package com.futsaltime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.net.URLClassLoader;

@SpringBootApplication
public class FutsalTimeApplication {

	public static void main(String[] args) {
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader)cl).getURLs();

		for(URL url: urls){
			System.out.println(url.getFile());
		}

		SpringApplication.run(FutsalTimeApplication.class, args);
	}
}
