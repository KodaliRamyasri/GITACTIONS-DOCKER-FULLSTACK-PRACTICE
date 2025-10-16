package com.klef.cicd.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.cicd.exam.model.FitnessUser;

@Repository
public interface FitnessUserRepository extends JpaRepository<FitnessUser, Integer> 
{
    FitnessUser findByEmail(String email);
}
