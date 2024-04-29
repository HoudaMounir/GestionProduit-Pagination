package com.example.GestionProduitPagination.controller;
import java.util.List;
import java.util.Optional;

import com.example.GestionProduitPagination.dao.ProduitRepository;
import com.example.GestionProduitPagination.entities.Produit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepo;
    @RequestMapping(value="/index")
    public String index(Model model,@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue ="3")int s,
                        @RequestParam(name="motcle",defaultValue ="")String mc) {
        Pageable pageable = PageRequest.of(p,s);
        Page<Produit> produitsPage = produitRepo.Chercher("%"+mc+"%",pageable);
        model.addAttribute("listProduits", produitsPage.getContent());

        int[] pages=new int[produitsPage.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante",p);
        model.addAttribute("motcle",mc);

        return "produits";
    }
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public String Delete(long id,String motcle,String page) {
        produitRepo.deleteById(id);
        return "redirect:/index?page=" + page +"&motcle=" + motcle;
    }

    @RequestMapping(value="/form",method=RequestMethod.GET)
    public String AjouterPrdouitPage(Model model) {
        model.addAttribute("produit", new Produit());
        return "nv_produit";
    }

    @RequestMapping(value="/ajout",method=RequestMethod.POST)
    public String AjouterPrdouit(Model model, @Valid Produit produit, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "nv_produit";
        }
        produitRepo.save(produit);
        return "confirmation";
    }
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String AjouterPrdouitPage(Model model,Long id) {
        Optional<Produit> p=produitRepo.findById(id);
        model.addAttribute("produit", p);
        return "edit_produit";
    }
}
