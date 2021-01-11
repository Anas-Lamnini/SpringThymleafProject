package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupesRepository extends JpaRepository<Groupe, Long>{

}
