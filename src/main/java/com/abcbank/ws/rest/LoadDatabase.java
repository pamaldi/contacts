package com.abcbank.ws.rest;

import com.abcbank.model.Contact;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.abcbank.repository.ContactRepository;

@Configuration
@Slf4j
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(ContactRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Contact("John", "Smith",LocalDate.of(1968, 2, 2),"via","via","030","030")));
			log.info("Preloading " + repository.save(new Contact("Bill", "Doe",LocalDate.of(1968, 2, 2),"via","via","030","030")));
			
		};
	}
}
