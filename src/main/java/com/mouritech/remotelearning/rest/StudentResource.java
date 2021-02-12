package com.mouritech.remotelearning.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.remotelearning.exceptions.RecordNotFoundException;
import com.mouritech.remotelearning.model.ContentDTO;
import com.mouritech.remotelearning.model.QualificationDTO;
import com.mouritech.remotelearning.model.RequestDTO;
import com.mouritech.remotelearning.model.SubjectDTO;
import com.mouritech.remotelearning.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentResource {

	@Autowired
	StudentService service;

	@GetMapping("/allqualification")
	public ResponseEntity<List<QualificationDTO>> getAllQualifications() {

		List<QualificationDTO> qualif = service.getQualification();
		if (qualif.isEmpty()) {
			throw new IllegalArgumentException("No qualification present");
		}
		return new ResponseEntity<List<QualificationDTO>>(qualif, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/subjects/{id}")
	public ResponseEntity<List<SubjectDTO>> getSubjectsByQualif(@PathVariable int id) {

		List<SubjectDTO> subject = service.getSubjectById(id);

		if (subject.isEmpty()) {
			throw new IllegalArgumentException("No Subects present by Id");
		}

		return new ResponseEntity<>(subject, HttpStatus.OK);

	}

	@GetMapping("/contents/{id}")
	public ResponseEntity<List<ContentDTO>> getContentsBySubj(@PathVariable int id) {

		List<ContentDTO> content = service.getContentById(id);

		if (content.isEmpty()) {
			throw new IllegalArgumentException("No content present by Id");
		}

		return new ResponseEntity<>(content, HttpStatus.OK);

	}

	@PostMapping("/request")
	public ResponseEntity<RequestDTO> requests(@RequestBody RequestDTO req) {

		RequestDTO request = service.requests(req);

		if (request == null) {

			throw new RecordNotFoundException("Request is not Exist");
		}
		return new ResponseEntity<>(request, HttpStatus.OK);

	}

	@GetMapping("/request/{id}")
	public ResponseEntity<List<RequestDTO>> getRequestByUserId(@PathVariable int userId) {

		List<RequestDTO> req = service.getRequestByUserId(userId);

		if (req.isEmpty()) {
			throw new IllegalArgumentException("No request present by this user");
		}
		return new ResponseEntity<>(req, HttpStatus.OK);

	}

	/*
	 * @PostMapping("/updaterequest/{id}") public ResponseEntity<List<RequestDTO>>
	 * getRequestByUserId(@PathVariable int Id,
	 * 
	 * @RequestBody Updatestatus updateStatus) {
	 * 
	 * int status = updateStatus.getCancelType();
	 * 
	 * if (Id == status) {
	 * 
	 * } return null; }
	 */
}
