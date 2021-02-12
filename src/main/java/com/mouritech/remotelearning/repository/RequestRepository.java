package com.mouritech.remotelearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.remotelearning.entity.Request;
import com.mouritech.remotelearning.entity.User;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	//@Query(" SELECT c FROM request c WHERE c.userId=:userId")
	public List<Request> findByUser(User userId);
	
}