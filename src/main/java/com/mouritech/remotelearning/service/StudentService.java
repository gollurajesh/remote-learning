package com.mouritech.remotelearning.service;

import java.util.List;

import com.mouritech.remotelearning.model.ContentDTO;
import com.mouritech.remotelearning.model.QualificationDTO;
import com.mouritech.remotelearning.model.RequestDTO;
import com.mouritech.remotelearning.model.SubjectDTO;

public interface StudentService {

	public List<QualificationDTO> getQualification();
	
	public List<SubjectDTO> getSubjectById(int id);
	
	public List<ContentDTO> getContentById(int id);
	
	public RequestDTO requests(RequestDTO requestDto);
	
	public List<RequestDTO> getRequestByUserId(int id);
}
