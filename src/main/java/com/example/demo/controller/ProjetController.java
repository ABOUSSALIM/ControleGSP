package com.example.demo.controller;
import com.example.demo.entities.Membre;
import com.example.demo.entities.Projet;
import com.example.demo.repository.MembreRepository;
import com.example.demo.repository.ProjetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjetController {
    @Autowired
    private ProjetRepository projetRep ;
    @GetMapping("/add")
    public String formuleCreation(Projet projet) {
        return "addprojet";
    }
    @GetMapping("/membres")
    public String lsAssigne(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "6") int size) {
        Page<Projet> projetsPage = projetRep.findAll(PageRequest.of(page, size));
        model.addAttribute("projetsPage", projetsPage);
        return "lsaffect";
    }
    @PostMapping("/project")
    public String ajouterProjet(@Valid Projet projet ,
                                BindingResult result ,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "6") int size,
                                Model model){
     if (result.hasErrors()) {
         model.addAttribute("projet", projet);
         return "addprojet";
     }
     projetRep.save(projet);
        Page<Projet> projetsPage = projetRep.findAll(PageRequest.of(page, size));
        model.addAttribute("projetsPage", projetsPage);
     return "lsaffect";
    }
    @GetMapping("/delete/{id}")
    public String deleteProjet(@PathVariable("id") Long id,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               Model model) {
        Projet projet = projetRep.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid projet id:" + id));
        projetRep.delete(projet);
        Page<Projet> projetsPage = projetRep.findAll(PageRequest.of(page, size));
        model.addAttribute("projetsPage", projetsPage);
        return "lsproj";
    }
    @GetMapping("/consulter/{id}")
    public String consulterProjet(@PathVariable("id") Long id, Model model) {
        Projet projet = projetRep.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid projet id:" + id));
        model.addAttribute("projet", projet);
        model.addAttribute("membres", projet.getMembres());
        return "details";
    }

    //Pagination
    @GetMapping("/collect")
    public String listProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            Model model) {
        Page<Projet> projetsPage = projetRep.findAll(PageRequest.of(page, size));
        if (projetsPage.getTotalElements() == 0 && page > 0) {
            return "redirect:/collect?page=" + (projetsPage.getTotalPages() - 1) + "&size=" + size;
        }
        model.addAttribute("projetsPage", projetsPage);
        return "lsproj";
    }

}

