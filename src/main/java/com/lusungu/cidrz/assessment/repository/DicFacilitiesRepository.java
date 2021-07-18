package com.lusungu.cidrz.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.DicFacilities;

public interface DicFacilitiesRepository extends JpaRepository<DicFacilities, Integer> {

	boolean existsByFacCode(String facCode);

}
