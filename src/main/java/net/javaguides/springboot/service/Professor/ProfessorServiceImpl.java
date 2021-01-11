package net.javaguides.springboot.service.Professor;

import net.javaguides.springboot.model.Professor;
import net.javaguides.springboot.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository ProfessorRepository;

	@Override
	public List<Professor> getAllProfessors() {
		return ProfessorRepository.findAll();
	}

	@Override
	public void saveProfessor(Professor employee) {
		this.ProfessorRepository.save(employee);
	}

	@Override
	public Professor getProfessoreById(long id) {
		Optional<Professor> optional = ProfessorRepository.findById(id);
		Professor employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteProfessorById(long id) {
		this.ProfessorRepository.deleteById(id);
	}

	@Override
	public Page<Professor> findPaginatedProfessor(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ProfessorRepository.findAll(pageable);
	}
}
