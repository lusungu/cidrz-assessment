package com.lusungu.cidrz.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lusungu.cidrz.assessment.model.Requests;

public interface RequestsRepository extends JpaRepository<Requests, Integer> {

}
