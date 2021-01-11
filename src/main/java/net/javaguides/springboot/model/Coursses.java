package net.javaguides.springboot.model;

import javax.persistence.*;
import net.javaguides.springboot.model.Professor;
@Entity
@Table(name = "Coursses")
public class Coursses {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "course_name")
	private String courseName;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor prof;

	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salle;

	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
}
