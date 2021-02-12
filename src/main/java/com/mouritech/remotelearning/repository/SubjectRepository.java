package com.mouritech.remotelearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.remotelearning.entity.Qualification;
import com.mouritech.remotelearning.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	 

	// @Query(" SELECT s FROM Subject s WHERE s.qualification =:qualificationId")
	public List<Subject> findByQualification(Qualification quaify);

}
