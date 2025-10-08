# dtoDemo
Démo pédagogique d’un projet Spring Boot illustrant l’utilisation des DTO (Data Transfer Objects) pour séparer les entités JPA de l’API exposée.
***
## :open_file_folder: Structure du projet
- `entity/` — contient les entités JPA (modèles persistés en base)
- `dto/`— contient les objets DTO (utilisés pour échanger avec les contrôleurs)
- `mapper/ `— contient les mappers manuels (conversion DTO ↔ Entity)
- `repository/` — interfaces Spring Data JPA
- `service/` — la couche métier, qui orchestre les conversions et les appels au repository
- `controller/` — les points d’entrée REST exposant l’API
- `application.properties` — configuration de la base de données, JPA, etc.
- `pom.xml` — configuration Maven, dépendances
***
## :dart: Objectifs pédagogiques
1. Séparer la couche persistante (entités / base de données) de la couche API (DTO)
2. Montrer comment convertir DTO → Entity et Entity → DTO
3. Préserver la sécurité et ne pas exposer de champs sensibles (ex : mot de passe)
4. Offrir un exemple simple d’API CRUD minimaliste
***
## :rocket: Prérequis
- Java 17 (ou version compatible avec Spring Boot utilisé)
- Maven
- MySQL (ou un serveur de base de données MySQL accessible)
****
## :wrench: Configuration de la base de données
Dans le fichier `src/main/resources/application.properties`, configurer les paramètres de connexion MySQL :
```
spring.datasource.url=jdbc:mysql://localhost:3306/dto_demo?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=TON_UTILISATEUR
spring.datasource.password=TON_MOT_DE_PASSE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
+ L’option `createDatabaseIfNotExist=true` permet de créer la base `dto_demo` automatiquement si elle n’existe pas.
+ Le paramètre `ddl-auto=update` indique à Hibernate de mettre à jour le schéma selon les entités.
***
## :running: Lancement de l’application
1. Construis le projet avec Maven :
```
bash

mvn clean install
```
2. Lance l’application Spring Boot :
```
bash

mvn spring-boot:run
```
L’API sera disponible par défaut sur `http://localhost:9000`.
***
## :package: Endpoints API
### :heavy_plus_sign: Créer un utilisateur (sous Postman par exemple)
```
POST http://localhost:9000/api/utilisateurs?password=secret
Content-Type: application/json

{
  "nom": "Dupont",
  "prenom": "Jean"
}
```
Réponse (exemple) :
```
json
{
  "id": 1,
  "nom": "Dupont",
  "prenom": "Jean"
}
```
### :mag_right: Récupérer tous les utilisateurs
```
GET http://localhost:9000/api/utilisateurs
```
Réponse
```
json
{
  "id": 1,
  "nom": "Dupont",
  "prenom": "Jean"
}
```
## :brain: Concepts clés à retenir
+ **Entité (Entity)** : classe mappée à une table de la base de données
+ **DTO (Data Transfer Object)** : classe utilisée pour l’échange de données via l’API, ne contenant que les champs nécessaires
+ **Mapper** : classe utilitaire qui convertit entre entité et DTO
+ **Service** : couche métier, qui effectue les conversions et les appels au repository
+ **Repository** : interface Spring Data JPA pour manipuler les entités en base
***
## :jigsaw: Extensions possibles / améliorations
+ Utiliser **MapStruct** pour générer automatiquement les mappers DTO ↔ Entity
+ Ajouter des validations (`@Valid`, `@NotNull`, etc.) sur les DTO
+ Gérer la mise à jour (UPDATE) et la suppression (DELETE) des utilisateurs
+ Ajouter des champs supplémentaires (email, rôles, date de création, etc.)
+ Mettre en place une authentification / sécurité (Spring Security)
+ Ajouter des tests unitaires / d’intégration









