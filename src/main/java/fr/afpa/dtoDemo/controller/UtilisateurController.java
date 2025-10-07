package fr.afpa.dtoDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.dtoDemo.dto.UtilisateurDTO;
import fr.afpa.dtoDemo.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    @Autowired
    private UtilisateurService service;

    @PostMapping()
    public UtilisateurDTO create(@RequestBody UtilisateurDTO dto, @RequestParam String password) {        
        return service.createUtilisateur(dto, password);
    }

    @GetMapping()
    public List<UtilisateurDTO> getAll() {
        return service.getAll();
    }
    
    
}
