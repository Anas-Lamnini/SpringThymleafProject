package net.javaguides.springboot.service.Professor;

import net.javaguides.springboot.model.Professor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorService {
	List<Professor> getAllProfessors();
	void saveProfessor(Professor employee);
	Professor getProfessoreById(long id);
	void deleteProfessorById(long id);
	Page<Professor> findPaginatedProfessor(int pageNo, int pageSize, String sortField, String sortDirection);
}
