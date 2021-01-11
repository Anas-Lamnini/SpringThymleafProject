package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Salle;
import net.javaguides.springboot.service.Salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalleController {

	@Autowired
	private SalleService salleService;
	
	// display list of employees
	@GetMapping("/Salles")
	public String viewHomePage(Model model) {
		return findPaginatedSalle(1, "number", "asc", model);
	}
	
	@GetMapping("/showNewSalleForm")
	public String showNewSalleForm(Model model) {
		// create model attribute to bind form data
		Salle salle = new Salle();
		model.addAttribute("salle", salle);
		return "new_salle";
	}
	
	@PostMapping("/saveSalle")
	public String saveSalle(@ModelAttribute("salle") Salle salle) {
		// save employee to database
		salleService.saveSalle(salle);
		return "redirect:/Salles";
	}
	
	@GetMapping("/showFormForUpdateSalle/{id}")
	public String showFormForUpdateSalle(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Salle salle = salleService.getSalleById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("salle", salle);
		return "update_salle";
	}
	
	@GetMapping("/deleteSalle/{id}")
	public String deleteSalle(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.salleService.deleteSalleById(id);
		return "redirect:/Salles";
	}
	
	
	@GetMapping("/pagesa/{pageNo}")
	public String findPaginatedSalle(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Salle> page = salleService.findPaginatedSalle(pageNo, pageSize, sortField, sortDir);
		List<Salle> listSalles = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listSalles", listSalles);
		return "indexSalle";
	}
}
