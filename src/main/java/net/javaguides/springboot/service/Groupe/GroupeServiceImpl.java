package net.javaguides.springboot.service.Groupe;


import net.javaguides.springboot.model.Groupe;
import net.javaguides.springboot.repository.GroupesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeServiceImpl implements GroupeService {

	@Autowired
	private GroupesRepository groupeRepository;

	@Override
	public List<Groupe> getAllGroupes() {
		return groupeRepository.findAll();
	}

	@Override
	public void saveGroupe(Groupe groupe) {
		this.groupeRepository.save(groupe);
	}

	@Override
	public Groupe getGroupeById(long id) {
		Optional<Groupe> optional = groupeRepository.findById(id);
		Groupe groupe = null;
		if (optional.isPresent()) {
			groupe = optional.get();
		} else {
			throw new RuntimeException(" Groupe not found for id :: " + id);
		}
		return groupe;
	}

	@Override
	public void deleteGroupeById(long id) {
		this.groupeRepository.deleteById(id);
	}

	@Override
	public Page<Groupe> findPaginatedGroupe(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.groupeRepository.findAll(pageable);
	}
}
