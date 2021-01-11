package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Professor;
import net.javaguides.springboot.service.Professor.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	// display list of employees
	@GetMapping("/Professor")
	public String viewHomePage(Model model) {

		return findPaginatedProfessor(1, "firstName", "asc", model);
	}
	
	@GetMapping("/showNewProfessorForm")
	public String showNewProfessorForm(Model model) {
		// create model attribute to bind form data
		Professor professor = new Professor();
		model.addAttribute("professor", professor);
		return "new_professor";
	}
	
	@PostMapping("/saveProfessor")
	public String saveProfessor(@ModelAttribute("professor") Professor professor) {
		// save employee to database
		professorService.saveProfessor(professor);
		return "redirect:/Professor";
	}
	
	@GetMapping("/showFormForUpdateProfessor/{id}")
	public String showFormForUpdateProfessor(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Professor professor = professorService.getProfessoreById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("professor", professor);
		return "update_professor";
	}
	
	@GetMapping("/deleteProfessor/{id}")
	public String deleteProfessor(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.professorService.deleteProfessorById(id);
		return "redirect:/Professor";
	}
	
	
	@GetMapping("/pagef/{pageNo}")
	public String findPaginatedProfessor(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Professor> page = professorService.findPaginatedProfessor(pageNo, pageSize, sortField, sortDir);
		List<Professor> listProfessors = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProfessors", listProfessors);
		return "indexProfessor";
	}
}
