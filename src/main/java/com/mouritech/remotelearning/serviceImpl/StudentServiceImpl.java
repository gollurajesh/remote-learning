package com.mouritech.remotelearning.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.remotelearning.entity.Content;
import com.mouritech.remotelearning.entity.Qualification;
import com.mouritech.remotelearning.entity.Request;
import com.mouritech.remotelearning.entity.Subject;
import com.mouritech.remotelearning.entity.User;
import com.mouritech.remotelearning.exceptions.RecordNotFoundException;
import com.mouritech.remotelearning.mappers.ModelMapperUtil;
import com.mouritech.remotelearning.mappers.StudentMapper;
import com.mouritech.remotelearning.mappers.UserMapper;
import com.mouritech.remotelearning.model.ContentDTO;
import com.mouritech.remotelearning.model.QualificationDTO;
import com.mouritech.remotelearning.model.RequestDTO;
import com.mouritech.remotelearning.model.SubjectDTO;
import com.mouritech.remotelearning.model.UserDTO;
import com.mouritech.remotelearning.repository.ContentRepository;
import com.mouritech.remotelearning.repository.QualificationRepository;
import com.mouritech.remotelearning.repository.RequestRepository;
import com.mouritech.remotelearning.repository.SubjectRepository;
import com.mouritech.remotelearning.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	QualificationRepository qualificationRepository;

	@Autowired
	SubjectRepository subjectRepo;
	
	@Autowired
	ContentRepository contentRepo;
	
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	RequestRepository requestRepo;
	
	@Autowired
	private ModelMapperUtil modelMapper;

	private static ModelMapper modelMappers = new ModelMapper();
	
	public List<QualificationDTO> getQualification() {

		List<Qualification> qualification = qualificationRepository.findAll();
		
		List<QualificationDTO> qualiDto = modelMappers.map(qualification, new TypeToken<List<QualificationDTO>>(){}.getType());

		return qualiDto;
				
	}

	public List<SubjectDTO> getSubjectById(int id) {

		Qualification quaify= new Qualification();
		quaify.setQualificationId(id);
		List<Subject> subj = subjectRepo.findByQualification(quaify);
		
		System.out.println("Subjects for qualification on Id:" + subj.size() );
		if (subj.isEmpty()) {
			throw new RecordNotFoundException("Subject not found with this id " + id);
		}
		 List<SubjectDTO> subDto = modelMappers.map(subj, new TypeToken<List<SubjectDTO>>(){}.getType());
		return subDto;
	}
	
	public List<ContentDTO> getContentById(int id) {

		Subject subj= new Subject();
		subj.setSubjectId(id);
		List<Content> cont = contentRepo.findBySubject(subj);
		if (cont.isEmpty()) {
			throw new RecordNotFoundException("Content not found with this id " + id);
		}
		List<ContentDTO> conDto = modelMappers.map(cont, new TypeToken<List<ContentDTO>>(){}.getType());
		return conDto;
		
	}

	public RequestDTO requests(RequestDTO requestDto) {

		Request map = modelMapper.map(requestDto, Request.class);
		Request req =requestRepo.save(map);

		return modelMapper.map(req, RequestDTO.class);
			
	}
	
	public List<RequestDTO> getRequestByUserId(int id) {
		
		User user= new User();
		user.setUserId(id);
		List<Request> req = requestRepo.findByUser(user);
		if (req.isEmpty()) {
			throw new RecordNotFoundException("request not found with this id " + id);
		}

		List<RequestDTO> reqDto = modelMappers.map(req, new TypeToken<List<RequestDTO>>(){}.getType());
		return reqDto;

	}
	
	/*
	 * public Request getStatusByRequest(int id) {
	 * 
	 * List<Request> req = requestRepo.findByRequestId(id); }
	 */
	
}

