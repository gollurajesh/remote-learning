package com.mouritech.remotelearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.remotelearning.entity.Content;
import com.mouritech.remotelearning.entity.Subject;

@Repository
public interface ContentRepository  extends JpaRepository<Content, Integer>{
	
	//@Query(" SELECT c FROM content c WHERE c.subjectId=:subjectId")
	public List<Content> findBySubject(Subject subjectId);
	
}
