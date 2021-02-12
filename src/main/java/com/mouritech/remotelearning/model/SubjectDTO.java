package com.mouritech.remotelearning.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SubjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int subjectId;
	
	private QualificationDTO qualification;
	
	//private int qualificationId; 

	private String subName;

	public QualificationDTO getQualification() {
		return qualification;
	}

	public void setQualification(QualificationDTO qualification) {
		this.qualification = qualification;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	
}
