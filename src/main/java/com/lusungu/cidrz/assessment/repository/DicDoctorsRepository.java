package com.lusungu.cidrz.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.DicDoctors;

public interface DicDoctorsRepository extends JpaRepository<DicDoctors, Integer> {

	boolean existsByDocCode(String facCode);

}
