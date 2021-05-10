package com.brixo.sytem.creditmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brixo.sytem.creditmanagement.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{

	Application findById(int id);
}
