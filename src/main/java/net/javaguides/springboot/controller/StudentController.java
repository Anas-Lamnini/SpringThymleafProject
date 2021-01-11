package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	// display list of employees
	@GetMapping("/Students")
	public String viewHomePage(Model model) {
		return findPaginatedStudent(1, "firstName", "asc", model);
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		// create model attribute to bind form data
		Student employee = new Student();
		model.addAttribute("Student", employee);
		return "new_Student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("Student") Student employee) {
		// save employee to database
		studentService.saveStudent(employee);
		return "redirect:/Students";
	}
	
	@GetMapping("/showFormForUpdateStudent/{id}")
	public String showFormForUpdateStudent(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Student employee = studentService.getStudentById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("Student", employee);
		return "update_Student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.studentService.deleteStudentById(id);
		return "redirect:/Students";
	}
	
	
	@GetMapping("/pagest/{pageNo}")
	public String findPaginatedStudent(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Student> page = studentService.findPaginatedStudent(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudents = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listStudents", listStudents);
		return "indexStudent";
	}
}
