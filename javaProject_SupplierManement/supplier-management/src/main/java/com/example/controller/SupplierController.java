package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Supplier;
import com.example.repository.SupplierRepository;

@Controller
public class SupplierController {

    private final SupplierRepository repo;

    public SupplierController(SupplierRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", repo.findAll());
        return "suppliers";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Supplier supplier) {
        repo.save(supplier);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", repo.findById(id).orElseThrow());
        model.addAttribute("suppliers", repo.findAll());
        return "suppliers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
