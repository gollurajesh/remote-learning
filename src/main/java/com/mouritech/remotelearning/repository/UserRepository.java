package com.mouritech.remotelearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mouritech.remotelearning.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	@Query("FROM User u where u.userEmail=:userEmail")
	public Optional<User> findUserByuserEmail(String userEmail);

	@Query("FROM User u where u.userName=:userName")
	public User findByUserName(String userName);

	@Query("FROM User u where u.userEmail=:userEmail")
	public User findByUserEmail(String userEmail);

}
