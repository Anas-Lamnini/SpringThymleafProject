package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "Groupes")
public class Groupe {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "groupes_name")
	private String groupesName;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupesName() {
		return groupesName;
	}

	public void setGroupesName(String groupesName) {
		this.groupesName = groupesName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
