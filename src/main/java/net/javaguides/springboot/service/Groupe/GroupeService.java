package net.javaguides.springboot.service.Groupe;

import net.javaguides.springboot.model.Groupe;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupeService {
	List<Groupe> getAllGroupes();
	void saveGroupe(Groupe employee);
	Groupe getGroupeById(long id);
	void deleteGroupeById(long id);
	Page<Groupe> findPaginatedGroupe(int pageNo, int pageSize, String sortField, String sortDirection);
}
