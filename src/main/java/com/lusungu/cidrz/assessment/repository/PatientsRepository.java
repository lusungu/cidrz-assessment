package com.lusungu.cidrz.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.Patients;

public interface PatientsRepository extends JpaRepository<Patients, Integer> {

	boolean existsByNrcAndNrcIsNotNull(String nrc);

}
