package net.javaguides.springboot.service.Salle;

import net.javaguides.springboot.model.Salle;
import net.javaguides.springboot.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServiceImpl implements SalleService {

	@Autowired
	private SalleRepository SalleRepository;

	@Override
	public List<Salle> getAllSalles() {
		return SalleRepository.findAll();
	}

	@Override
	public void saveSalle(Salle employee) {
		this.SalleRepository.save(employee);
	}

	@Override
	public Salle getSalleById(long id) {
		Optional<Salle> optional = SalleRepository.findById(id);
		Salle employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteSalleById(long id) {
		this.SalleRepository.deleteById(id);
	}

	@Override
	public Page<Salle> findPaginatedSalle(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.SalleRepository.findAll(pageable);
	}
}
