package com.mouritech.remotelearning.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.remotelearning.entity.Content;
import com.mouritech.remotelearning.entity.Qualification;
import com.mouritech.remotelearning.entity.Request;
import com.mouritech.remotelearning.entity.Subject;
import com.mouritech.remotelearning.model.ContentDTO;
import com.mouritech.remotelearning.model.QualificationDTO;
import com.mouritech.remotelearning.model.RequestDTO;
import com.mouritech.remotelearning.model.SubjectDTO;

@Component
public class StudentMapper {

	@Autowired
	private ModelMapper mapper;

	public SubjectDTO subToSubDto(Subject sub) {
		return mapper.map(sub, SubjectDTO.class);
	}

	public Subject subDtoToSub(SubjectDTO subDTO) {
		return mapper.map(subDTO, Subject.class);
	}

	public ContentDTO conToConDto(Content con) {
		return mapper.map(con, ContentDTO.class);
	}

	public Content conDtoTocon(ContentDTO conDTO) {
		return mapper.map(conDTO, Content.class);
	}

	public QualificationDTO qualiToQualiDto(Qualification quali) {
		return mapper.map(quali, QualificationDTO.class);
	}

	public Qualification qualiDtoToQuali(QualificationDTO qualiDTO) {
		return mapper.map(qualiDTO, Qualification.class);
	}

	public RequestDTO reqToReqDto(Request req) {
		return mapper.map(req, RequestDTO.class);
	}

	public Request reqDtoToReq(RequestDTO reqDTO) {
		return mapper.map(reqDTO, Request.class);
	}
}
