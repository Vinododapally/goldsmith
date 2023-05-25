package com.gold.smith.users.controller;

import com.gold.smith.exception.ResourceNotFoundException;
import com.gold.smith.users.model.User;
import com.gold.smith.users.repository.UserRepository;
import com.gold.smith.util.BaseConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = BaseConfig.baseUrl)
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        user.setPassword(userDetails.getPassword());
        user.setUsername(userDetails.getUsername());
        user.setMobileNumber(userDetails.getMobileNumber());
        return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/user/{name}")
//    public boolean getUserByName(@PathVariable(value = "name") String userName) {
//        User user = userRepository.findByUsername(userName);
//        if(user!=null && user.getUsername()!=null){
//            return true;
//        }
//        return false;
//    }

}
