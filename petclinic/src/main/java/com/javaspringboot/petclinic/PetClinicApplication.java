package com.javaspringboot.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
@EnableConfigurationProperties(value=PetClinicProperties.class)
public class PetClinicApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(PetClinicApplication.class, args);

	}

}
