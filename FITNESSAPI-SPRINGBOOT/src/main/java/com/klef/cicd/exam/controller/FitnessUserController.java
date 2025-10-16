package com.klef.cicd.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.cicd.exam.model.FitnessUser;
import com.klef.cicd.exam.service.FitnessUserService;


@RestController
@RequestMapping("/fitnessapi/")
@CrossOrigin(origins = "*")
public class FitnessUserController {

    @Autowired
    private FitnessUserService fitnessUserService;

    @GetMapping("/")
    public String home() {
        return "Fitness Tracker Backend is Running";
    }

    @GetMapping("/info")
    public String info() {
        return "Fitness Tracker Full Stack Deployment Demo";
    }

    @PostMapping("/add")
    public ResponseEntity<FitnessUser> addUser(@RequestBody FitnessUser user) {
        FitnessUser savedUser = fitnessUserService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FitnessUser>> getAllUsers() {
        List<FitnessUser> users = fitnessUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        FitnessUser user = fitnessUserService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody FitnessUser user) {
        FitnessUser existing = fitnessUserService.getUserById(user.getId());
        if (existing != null) {
            FitnessUser updatedUser = fitnessUserService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. User with ID " + user.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        FitnessUser existing = fitnessUserService.getUserById(id);
        if (existing != null) {
            fitnessUserService.deleteUserById(id);
            return new ResponseEntity<>("User with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. User with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
