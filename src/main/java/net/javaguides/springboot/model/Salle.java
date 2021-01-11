package net.javaguides.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Salle {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "Batiement")
	private String batiement;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBatiement() {
		return batiement;
	}

	public void setBatiement(String batiement) {
		this.batiement = batiement;
	}
}
