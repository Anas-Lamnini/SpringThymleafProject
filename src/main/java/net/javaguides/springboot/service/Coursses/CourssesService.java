package net.javaguides.springboot.service.Coursses;

import net.javaguides.springboot.model.Coursses;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourssesService {
	List<Coursses> getAllCoursses();
	void saveCoursse(Coursses employee);
	Coursses getCoursseById(long id);
	void deleteCoursseById(long id);
	Page<Coursses> findPaginatedCoursses(int pageNo, int pageSize, String sortField, String sortDirection);
}
