package net.javaguides.springboot.service.Salle;

import net.javaguides.springboot.model.Salle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SalleService {
	List<Salle> getAllSalles();
	void saveSalle(Salle employee);
	Salle getSalleById(long id);
	void deleteSalleById(long id);
	Page<Salle> findPaginatedSalle(int pageNo, int pageSize, String sortField, String sortDirection);
}
