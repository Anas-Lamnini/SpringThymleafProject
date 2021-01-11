package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Groupe;
import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.service.Groupe.GroupeService;
import net.javaguides.springboot.service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupeController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private GroupeService groupeService;
	
	// display list of Groupes
	@GetMapping("/groupe")
	public String viewHomePage(Model model) {

		return findPaginatedGroupe(1, "groupesName", "asc", model);
	}
	
	@GetMapping("/showNewGroupeForm")
	public String showNewGroupeForm (Model model) {
		// create model attribute to bind form data
		List<Student> listStudents= studentService.getAllStudents();
		Groupe groupe = new Groupe();
		model.addAttribute("groupe", groupe);
		model.addAttribute("listStudents", listStudents);
		return "new_groupe";
	}
	@GetMapping("/displaystentsgroupe/{id}")
	public String displaystentsgroupe (@PathVariable ( value = "id") long id,Model model) {
		// create model attribute to bind form data
		List<Student> listStudents= studentService.getAllStudents();
		Groupe groupe = groupeService.getGroupeById(id);
		model.addAttribute("groupe", groupe);
		model.addAttribute("listStudents", listStudents);
		return "StudentsList";
	}

	@PostMapping("/saveGroupe")
	public String saveGroupe(@ModelAttribute("groupe") Groupe groupe) {
		// save groupe to database
		groupeService.saveGroupe(groupe);
		return "redirect:/groupe";
	}
	
	@GetMapping("/showFormForUpdateGroupe/{id}")
	public String showFormForUpdateGroupe(@PathVariable ( value = "id") long id, Model model) {
		List<Student> listStudents= studentService.getAllStudents();
		// get groupe from the service
		Groupe groupe = groupeService.getGroupeById(id);

		// set groupes as a model attribute to pre-populate the form
		model.addAttribute("groupe", groupe);
		model.addAttribute("listStudents", listStudents);
		return "update_groupe";
	}
	
	@GetMapping("/deleteGroupe/{id}")
	public String deleteGroupe(@PathVariable (value = "id") long id) {
		
		// call delete groupe method
		this.groupeService.deleteGroupeById(id);
		return "redirect:/groupe";
	}
	
	
	@GetMapping("/pageg/{pageNo}")
	public String findPaginatedGroupe(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;

		Page<Groupe> page = groupeService.findPaginatedGroupe(pageNo, pageSize, sortField, sortDir);
		List<Groupe> listGroupes = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listGroupes", listGroupes);
		return "indexGroupe";
	}
}
