package com.mouritech.remotelearning.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class QualificationDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int qualificationId;
	
	private String qualification;
	
	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	
	
	
}

