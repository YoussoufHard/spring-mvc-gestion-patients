package ma.enset.hopital.repository;

import ma.enset.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //premier methode
    Page<Patient> findByNomContains(String keyword , Pageable pageable);

    //deuxieme methode

    @Query("select p from Patient p where p.nom like :x")
    Page<Patient> Chercher(@Param("x") String x, Pageable pageable);
}
