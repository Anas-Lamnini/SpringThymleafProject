package net.javaguides.springboot.service.Student;

import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository StudentRepository;

	@Override
	public List<Student> getAllStudents() {
		return StudentRepository.findAll();
	}

	@Override
	public void saveStudent(Student employee) {
		this.StudentRepository.save(employee);
	}

	@Override
	public Student getStudentById(long id) {
		Optional<Student> optional = StudentRepository.findById(id);
		Student employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteStudentById(long id) {
		this.StudentRepository.deleteById(id);
	}

	@Override
	public Page<Student> findPaginatedStudent(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.StudentRepository.findAll(pageable);
	}
}
