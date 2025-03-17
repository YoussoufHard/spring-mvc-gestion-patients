package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories("ma.enset.hopital.repository")  // Spécifie où chercher les repositories
@EntityScan(basePackages = "ma.enset.hopital.entities")

public class HopitalApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(Patient.builder().nom("ahmed").dateNaissance(new Date()).malade(true).score(10).build());
		patientRepository.save(Patient.builder().nom("Youssouf").dateNaissance(new Date()).malade(false).score(123).build());
		patientRepository.save(Patient.builder().nom("Salma").dateNaissance(new Date()).malade(true).score(90).build());
		patientRepository.save(Patient.builder().nom("Hajar").dateNaissance(new Date()).malade(false).score(34).build());

	}

}
