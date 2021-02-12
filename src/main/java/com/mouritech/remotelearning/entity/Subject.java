package com.mouritech.remotelearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sub_id")
	private int subjectId;
	
	
	 @ManyToOne 
	 @JoinColumn(name="qualification_id") 
	 //@Column(name="qualification_id")
	 private Qualification qualification;
	 
	
//	@Column(name="qualification_id")
//	private int qualificationId;
	
	@Column(name="sub_name")
	private String subName;


	public int getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}


	public Qualification getQualification() {
		return qualification;
	}


	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}


	public String getSubName() {
		return subName;
	}


	public void setSubName(String subName) {
		this.subName = subName;
	}

	
	
}

