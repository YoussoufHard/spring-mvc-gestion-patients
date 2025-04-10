package ma.enset.hopital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data @NoArgsConstructor  @AllArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Le nom est obligatoire")
    @Size(min = 4, max = 40, message = "Le nom doit contenir au moins 4 caractères")
    private String nom;
    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date doit être dans le passé")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade ;
    @DecimalMax(value = "300", message = "le score maximal doit etre 300")
    private int score ;
}
