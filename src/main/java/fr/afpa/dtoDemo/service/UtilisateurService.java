// Déclare le package où se trouve cette classe.
// Ici, la classe appartient au package "service", qui contient la logique métier de l’application.
package fr.afpa.dtoDemo.service;

// Importation des classes nécessaires à cette classe de service.
import java.util.List;
import java.util.stream.Collectors; // Permet de transformer une liste avec la méthode stream()

// Annotation Spring qui déclare cette classe comme un "Service" (composant métier Spring).
// Cela permet à Spring de la détecter et de l’injecter là où elle est utilisée.
import org.springframework.stereotype.Service;

// Importation du DTO (objet transféré vers/depuis l’API).
import fr.afpa.dtoDemo.dto.UtilisateurDTO;

// Importation de l’entité JPA (correspondant à la table "utilisateur" dans la base).
import fr.afpa.dtoDemo.entity.Utilisateur;

// Importation du mapper (classe utilitaire qui convertit entre DTO et Entity).
import fr.afpa.dtoDemo.mapper.UtilisateurMapper;

// Importation du repository, qui s’occupe des interactions avec la base de données.
import fr.afpa.dtoDemo.repository.UtilisateurRepository;

// Importation d’une annotation Lombok.
// @RequiredArgsConstructor génère automatiquement un constructeur avec les champs "final" comme paramètres.
import lombok.RequiredArgsConstructor;

// Déclare la classe comme un "Service" Spring.
// Cela signifie que Spring va la gérer comme un bean injectable.
@Service

// Lombok crée automatiquement un constructeur prenant comme paramètre le repository (car il est final).
// Cela permet d’utiliser l’injection de dépendance sans avoir besoin d’écrire le constructeur soi-même.
@RequiredArgsConstructor
public class UtilisateurService {

    // Déclare le repository en tant que dépendance.
    // Grâce à @RequiredArgsConstructor, il sera injecté automatiquement par Spring.
    private final UtilisateurRepository repository;

    // --- Méthode 1 : création d’un utilisateur ---
    // Cette méthode reçoit un DTO et un mot de passe, puis enregistre un nouvel utilisateur en base.
    public UtilisateurDTO createUtilisateur(UtilisateurDTO dto, String password) {

        // Conversion du DTO (provenant du contrôleur/API) en entité JPA.
        Utilisateur utilisateur = UtilisateurMapper.dtoToEntity(dto);

        // On définit le mot de passe manuellement, car il ne vient pas du DTO (pour des raisons de sécurité).
        utilisateur.setPassword(password);

        // On enregistre l’entité dans la base via le repository.
        // repository.save() renvoie l’entité persistée (avec un id généré).
        Utilisateur saved = repository.save(utilisateur);

        // On convertit à nouveau l’entité sauvegardée en DTO
        // afin de la renvoyer au contrôleur (et donc à la réponse API).
        return UtilisateurMapper.entityToDto(saved);
    }

    // --- Méthode 2 : récupération de tous les utilisateurs ---
    public List<UtilisateurDTO> getAll() {

        // repository.findAll() retourne une liste d'entités Utilisateur depuis la base de données.
        // On utilise un flux (stream) pour transformer chaque entité en DTO.
        return repository.findAll().stream()

                // Pour chaque entité, on applique la méthode de conversion entityToDto().
                .map(UtilisateurMapper::entityToDto)

                // On collecte le résultat dans une nouvelle liste.
                .collect(Collectors.toList());
    }
}
