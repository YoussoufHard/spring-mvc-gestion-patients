package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	// @Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(Patient.builder().nom("Ahmed").dateNaissance(new Date()).malade(true).score(10).build());
			patientRepository.save(Patient.builder().nom("Youssouf").dateNaissance(new Date()).malade(false).score(123).build());
			patientRepository.save(Patient.builder().nom("Salma").dateNaissance(new Date()).malade(true).score(90).build());
			patientRepository.save(Patient.builder().nom("Hajar").dateNaissance(new Date()).malade(false).score(34).build());
		};
	}
}
