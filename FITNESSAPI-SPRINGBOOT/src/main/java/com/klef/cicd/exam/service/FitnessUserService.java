package com.klef.cicd.exam.service;

import java.util.List;

import com.klef.cicd.exam.model.FitnessUser;

public interface FitnessUserService {
    FitnessUser addUser(FitnessUser user);
    List<FitnessUser> getAllUsers();
    FitnessUser getUserById(int id);
    FitnessUser updateUser(FitnessUser user);
    void deleteUserById(int id);
}
