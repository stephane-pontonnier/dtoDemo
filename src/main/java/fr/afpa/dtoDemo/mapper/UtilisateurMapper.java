// Déclare le package dans lequel se trouve cette classe.
// Cela permet de regrouper logiquement le code (ici : les classes "mapper").
package fr.afpa.dtoDemo.mapper;

// Importation de la classe UtilisateurDTO (Data Transfer Object)
// qui représente une version simplifiée de l'entité pour échanger avec l'extérieur.
import fr.afpa.dtoDemo.dto.UtilisateurDTO;

// Importation de la classe Utilisateur (l'entité JPA qui correspond à la table dans la base de données).
import fr.afpa.dtoDemo.entity.Utilisateur;

// Déclaration publique de la classe UtilisateurMapper.
// Son rôle : convertir les objets "DTO" en "Entity" et inversement.
public class UtilisateurMapper {

    // --- Conversion du DTO vers l'entité ---
    // Cette méthode prend un objet UtilisateurDTO en entrée
    // et renvoie un objet Utilisateur (entité JPA) équivalent.
    public static Utilisateur dtoToEntity(UtilisateurDTO dto) {

        // On crée une nouvelle instance d'Utilisateur vide.
        Utilisateur utilisateur = new Utilisateur();

        // On copie la valeur de l'id depuis le DTO vers l'entité.
        utilisateur.setId(dto.getId());

        // On copie le nom depuis le DTO vers l'entité.
        utilisateur.setNom(dto.getNom());

        // On copie le prénom depuis le DTO vers l'entité.
        utilisateur.setPrenom(dto.getPrenom());

        // ⚠️ Le mot de passe (password) n’est pas présent dans le DTO,
        // donc on ne le renseigne pas ici pour éviter de l’exposer dans les transferts de données.
        // Il sera défini ailleurs (par exemple dans le service).
        
        // On retourne l’objet Utilisateur (entité) complet.
        return utilisateur;
    }

    // --- Conversion de l'entité vers le DTO ---
    // Cette méthode prend un objet Utilisateur (entité JPA)
    // et renvoie un objet UtilisateurDTO simplifié (sans le mot de passe).
    public static UtilisateurDTO entityToDto(Utilisateur entity) {

        // On crée une nouvelle instance de DTO vide.
        UtilisateurDTO dto = new UtilisateurDTO();

        // On copie l'identifiant de l'entité vers le DTO.
        dto.setId(entity.getId());

        // On copie le nom de l'entité vers le DTO.
        dto.setNom(entity.getNom());

        // On copie le prénom de l'entité vers le DTO.
        dto.setPrenom(entity.getPrenom());

        // Le mot de passe n’est pas copié volontairement :
        // on ne veut pas exposer des données sensibles dans l’API.

        // On retourne le DTO final, prêt à être envoyé dans la réponse HTTP.
        return dto;
    }
}
