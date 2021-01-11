package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Coursses;
import net.javaguides.springboot.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourssesRepository extends JpaRepository<Coursses , Long>{

}
