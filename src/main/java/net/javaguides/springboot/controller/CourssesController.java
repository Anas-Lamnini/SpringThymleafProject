package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Coursses;
import net.javaguides.springboot.model.Groupe;
import net.javaguides.springboot.model.Professor;
import net.javaguides.springboot.model.Salle;
import net.javaguides.springboot.service.Coursses.CourssesService;
import net.javaguides.springboot.service.Groupe.GroupeService;
import net.javaguides.springboot.service.Professor.ProfessorService;
import net.javaguides.springboot.service.Salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourssesController {

	@Autowired
	private ProfessorService professorService;
	@Autowired
	private GroupeService groupeService;
	@Autowired
	private SalleService salleService;
	@Autowired
	private CourssesService coursService;
	
	// display list of employees
	@GetMapping("/cours")
	public String viewHomePage(Model model) {

		return findPaginatedCoursses(1, "courseName", "asc", model);
	}
	
	@GetMapping("/showNewCoursForm")
	public String showNewCoursForm (Model model) {
		// create model attribute to bind form data
		List<Professor> listProfessors= professorService.getAllProfessors();
		List<Salle> listSalle= salleService.getAllSalles();
		List<Groupe> listGroupe= groupeService.getAllGroupes();

		Coursses cours = new Coursses();
		model.addAttribute("cours", cours);
		model.addAttribute("listProfessors", listProfessors);
		model.addAttribute("listSalle", listSalle);
		model.addAttribute("listGroupe", listGroupe);

		return "new_cours";
	}
	
	@PostMapping("/saveCours")
	public String saveCours(@ModelAttribute("cours") Coursses cours) {
		// save employee to database
		coursService.saveCoursse(cours);
		return "redirect:/cours";
	}
	
	@GetMapping("/showFormForUpdateCours/{id}")
	public String showFormForUpdateCours(@PathVariable ( value = "id") long id, Model model) {
		List<Professor> listProfessors= professorService.getAllProfessors();
		List<Salle> listSalle= salleService.getAllSalles();
		List<Groupe> listGroupe= groupeService.getAllGroupes();

		// get employee from the service
		Coursses cours = coursService.getCoursseById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("cours", cours);
		model.addAttribute("listProfessors", listProfessors);
		model.addAttribute("listSalle", listSalle);
		model.addAttribute("listGroupe", listGroupe);

		return "update_coursses";
	}
	
	@GetMapping("/deleteCoursse/{id}")
	public String deleteCoursse(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.coursService.deleteCoursseById(id);
		return "redirect:/cours";
	}
	
	
	@GetMapping("/pagec/{pageNo}")
	public String findPaginatedCoursses(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Coursses> page = coursService.findPaginatedCoursses(pageNo, pageSize, sortField, sortDir);
		List<Coursses> listCoursses = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCoursses", listCoursses);
		return "indexCoursse";
	}
}
