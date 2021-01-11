package net.javaguides.springboot.service.Coursses;

import net.javaguides.springboot.model.Coursses;
import net.javaguides.springboot.repository.CourssesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourssesServiceImpl implements CourssesService {

	@Autowired
	private CourssesRepository coursRepository;

	@Override
	public List<Coursses> getAllCoursses() {
		return coursRepository.findAll();
	}

	@Override
	public void saveCoursse(Coursses employee) {
		this.coursRepository.save(employee);
	}

	@Override
	public Coursses getCoursseById(long id) {
		Optional<Coursses> optional = coursRepository.findById(id);
		Coursses employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Coursse not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteCoursseById(long id) {
		this.coursRepository.deleteById(id);
	}

	@Override
	public Page<Coursses> findPaginatedCoursses(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.coursRepository.findAll(pageable);
	}
}
