package com.example.demo.controller;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Projet;
import com.example.demo.repository.MembreRepository;
import com.example.demo.repository.ProjetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class MembreController {
    @Autowired
    private ProjetRepository projetRep ;
    @Autowired
    private MembreRepository membreRep;
        @GetMapping("/newmembre/{id}")
    public String formuleMembre(@PathVariable("id") Long id,Membre membre, Model model) {
            model.addAttribute("idProjet", id);
        return "addmembre";
    }
        @PostMapping("/assigner/{id}")
    public String assignerMembre(@PathVariable("id") Long id, @Valid Membre membre, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("membre", membre);
            return "addmembre";
        }
        Projet projet = projetRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
            Membre newM = new Membre();
            newM.setNom(membre.getNom());
            newM.setRole(membre.getRole());
            newM.setProjet(projet);
            membreRep.save(newM);
            projet.getMembres().add(newM);
            return "redirect:/membres";
    }
}
