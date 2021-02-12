package com.mouritech.remotelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.remotelearning.entity.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {

}
