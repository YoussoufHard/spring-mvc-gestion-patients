package ma.enset.hopital.security;

import lombok.AllArgsConstructor;
import ma.enset.hopital.security.services.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailServiceImpl userDetailServiceImpl;
    private PasswordEncoder passwordEncoder; // Sera injecté automatiquement
   // @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager (DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource) ;
    }


    // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        String encodedPassword = passwordEncoder.encode("1234");
        System.out.println("Password Encoded : " + encodedPassword);

        UserDetails user1 = User.withUsername("user1").password(encodedPassword).roles("USER").build();
        UserDetails user2 = User.withUsername("user2").password(encodedPassword).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(encodedPassword).roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form -> form
                        .loginPage("/login") // page personnalisée
                        .defaultSuccessUrl("/", true) // redirection après succès
                        .permitAll() // accès public à la page de login
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret") // une clé de sécurité
                        .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 jours
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/webjars/**", "/h2-console/**").permitAll() // autorise Bootstrap, icons, etc.
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/notAuthorized")
                )
                .userDetailsService(userDetailServiceImpl)
                .build();
    }

}
