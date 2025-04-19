package ma.enset.hopital.security.services;

import lombok.AllArgsConstructor;
import ma.enset.hopital.security.entities.AppRole;
import ma.enset.hopital.security.entities.AppUser;
import ma.enset.hopital.security.repo.AppRoleRepository;
import ma.enset.hopital.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) throw new RuntimeException("This user already exists");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords don't match");
        appUser=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedUser;
        savedUser = appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    @Transactional
    public AppRole addNewRole(String role) {
        Optional<AppRole> existingRole = appRoleRepository.findById(role);
        if (existingRole.isPresent()) {
            return existingRole.get();
        }
        AppRole newRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(newRole);
    }


    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found");

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        appUser.getRoles().add(appRole);
      //  appUserRepository.save(appUser);      // Pas besoin de save(appUser) grâce à @Transactional
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found");

        AppRole appRole = appRoleRepository.findById(role)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
