package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import ma.enset.hopital.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	// @Bean
	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(Patient.builder().nom("Ahmed").dateNaissance(new Date()).malade(true).score(10).build());
			patientRepository.save(Patient.builder().nom("Youssouf").dateNaissance(new Date()).malade(false).score(123).build());
			patientRepository.save(Patient.builder().nom("Salma").dateNaissance(new Date()).malade(true).score(90).build());
			patientRepository.save(Patient.builder().nom("Hajar").dateNaissance(new Date()).malade(false).score(34).build());
		};
	}

	// @Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
		 PasswordEncoder passwordEncoder=passwordEncoder(); // Sera injecté automatiquement
		return args -> {
			if (!jdbcUserDetailsManager.userExists("user11")) {
				UserDetails user1 = User.withUsername("user11")
						.password(passwordEncoder.encode("1234"))
						.roles("USER")
						.build();
				jdbcUserDetailsManager.createUser(user1);
			}

			if (!jdbcUserDetailsManager.userExists("user22")) {
				UserDetails user2 = User.withUsername("user22")
						.password(passwordEncoder.encode("1234"))
						.roles("USER")
						.build();
				jdbcUserDetailsManager.createUser(user2);
			}

			if (!jdbcUserDetailsManager.userExists("admin2")) {
				UserDetails admin = User.withUsername("admin2")
						.password(passwordEncoder.encode("1234"))
						.roles("USER", "ADMIN")
						.build();
				jdbcUserDetailsManager.createUser(admin);
			}
		};
	}

	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
		PasswordEncoder passwordEncoder=passwordEncoder(); // Sera injecté automatiquement
		return args -> {
			accountService.addNewRole("USER") ;
			accountService.addNewRole("ADMIN") ;
			accountService.addNewUser("user1","1234","user1@gmail.com","1234") ;
			accountService.addNewUser("user2","1234","user2@gmail.com","1234") ;
			accountService.addNewUser("admin","1234","admin@gmail.com","1234") ;

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");

		} ;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
