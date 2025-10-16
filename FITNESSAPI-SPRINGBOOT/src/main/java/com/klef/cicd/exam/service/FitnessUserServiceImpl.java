package com.klef.cicd.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.cicd.exam.model.FitnessUser;
import com.klef.cicd.exam.repository.FitnessUserRepository;


@Service
public class FitnessUserServiceImpl implements FitnessUserService {

    @Autowired
    private FitnessUserRepository fitnessUserRepository;

    @Override
    public FitnessUser addUser(FitnessUser user) {
        return fitnessUserRepository.save(user);
    }

    @Override
    public List<FitnessUser> getAllUsers() {
        return fitnessUserRepository.findAll();
    }

    @Override
    public FitnessUser getUserById(int id) {
        Optional<FitnessUser> opt = fitnessUserRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public FitnessUser updateUser(FitnessUser user) {
        return fitnessUserRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        fitnessUserRepository.deleteById(id);
    }
}
