# 🏥 Gestion des Patients avec Spring Boot

## 📌 Description
Ce projet est une **application web JEE** basée sur **Spring Boot**, **Spring MVC**, **Thymeleaf** et **Spring Data JPA**. Elle permet de **gérer les patients** avec différentes fonctionnalités, telles que la pagination, la recherche et la suppression.

Le projet est développé en plusieurs **parties** :
1. **Partie 1** : Gestion des patients (CRUD, pagination, recherche, suppression).
2. **Partie 2** : Ajout d'un **template Thymeleaf** et validation des formulaires.
3. **Partie 3** : Sécurisation avec **Spring Security** (InMemory, JDBC, UserDetailsService).

---

## 🛠 Technologies utilisées
- **Spring Boot 3.2** (Framework principal)
- **Spring Data JPA** (Gestion de la base de données)
- **Spring MVC** (Gestion des requêtes et vues)
- **Thymeleaf** (Moteur de templates pour le frontend)
- **Bootstrap 5.1.1** (Design responsive)
- **Spring Security** (Sécurisation des accès)
- **H2 / MySQL** (Base de données)

---

## 🚀 Installation et Exécution

### 1️⃣ Cloner le projet
```bash
git clone https://github.com/YoussoufHard/spring-mvc-gestion-patients.git
cd spring-mvc-gestion-patients
```

### 2️⃣ Configuration de la base de données
Dans **`application.properties`**, sélectionner H2 ou MySQL :
```properties
# H2 (mode mémoire)
spring.datasource.url=jdbc:h2:mem:patients_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# MySQL (décommenter si nécessaire)
# spring.datasource.url=jdbc:mysql://localhost:3306/patients_db
# spring.datasource.username=root
# spring.datasource.password=ton_mdp
# spring.jpa.hibernate.ddl-auto=update
```
Capture de la base de données creer dans mysql apres l'execution

 ![Structure de la base de données](captures/img.png)

### 3️⃣ Lancer l’application
```bash
mvn spring-boot:run
```
Ou dans **IntelliJ**, exécuter la classe `HopitalApplication`.

### 4️⃣ Accéder à l’application
🌍 **URL principale** : `http://localhost:8080/`  
🛠 **Console H2** : `http://localhost:8080/h2-console`

---

## 📂 Structure du projet
```
📦 ma.enset.hopital
 ┣ 📂 entities
 ┃ ┗ 📜 Patient.java
 ┣ 📂 repository
 ┃ ┗ 📜 PatientRepository.java
 ┣ 📂 web
 ┃ ┗ 📜 PatientController.java
 ┣ 📂 security
 ┃ ┗ 📜 SecurityConfig.java
 ┗ 📜 HopitalApplication.java
```

---

## 📜 Fonctionnalités

### ✅ Partie 1 : Gestion des patients
📄 **Afficher les patients**  
🔍 **Rechercher un patient par nom**  
❌ **Supprimer un patient**  
📑 **Pagination des résultats**

🌍 **URL :** `http://localhost:8080/index`

📌 Tutoriel : [📹 Partie 1](https://www.youtube.com/watch?v=jDm-q-jEbiA)

## Aperçu de l'interface
Voici un aperçu de la page d'accueil du système avant pagination:

![Page d'accueil](/captures/img_1.png) 

Voici un aperçu de la page d'accueil du système après pagination :

![Page d'accueil paginé](/captures/img_2.png)

Voici un aperçu de la page d'accueil du système avec l'option de Recherche:

![Page d'accueil paginé](/captures/img_3.png)
---

### 🎨 Partie 2 : Templates et validation
📌 **Ajout d'un design Bootstrap pour rendre l'interface plus conviviale**  
📌 **Validation des formulaires avec Spring Boot**

📌 Tutoriel : [📹 Partie 2](https://www.youtube.com/watch?v=eoBE745lDE0)

---

### 🔒 Partie 3 : Sécurité avec Spring Security
1️⃣ **InMemory Authentication** ([📹 Tutoriel](https://www.youtube.com/watch?v=7VqpC8UD1zM))  
2️⃣ **JDBC Authentication** ([📹 Tutoriel](https://www.youtube.com/watch?v=Haz3wLiQ5-0))  
3️⃣ **UserDetailsService** ([📹 Tutoriel](https://www.youtube.com/watch?v=RTiS9ygyYs4))

**Configuration de la sécurité** :
```java
@Bean
public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
    String encodedPassword = passwordEncoder.encode("1234");
    return new InMemoryUserDetailsManager(
        User.withUsername("user1").password(encodedPassword).roles("USER").build(),
        User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
    );
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .formLogin(Customizer.withDefaults())
        .authorizeHttpRequests(ar->ar.requestMatchers("/deletePatient/**").hasRole("ADMIN"))
        .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
        .build();
}
```

---

## ✅ Suivi des commits
📌 **Bonnes pratiques** :
- Un commit **toutes les 30 minutes**
- Un **push** après chaque modification importante
- Un **dernier commit** à la fin du TP

---

## 📑 Auteur
👨‍💻 **Ton Nom** - *Projet réalisé dans le cadre du TP Spring Boot*

---

💡 **Le projet sera amélioré progressivement avec les nouvelles fonctionnalités.** 🚀