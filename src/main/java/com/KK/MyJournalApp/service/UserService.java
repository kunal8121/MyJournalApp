package com.KK.MyJournalApp.service;

import com.KK.MyJournalApp.Entity.User;
import com.KK.MyJournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepository;

    public User saveNewUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public User saveAdmin(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        return userRepository.save(user);
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId myId)
    {
        return userRepository.findById(myId);
    }

    public void deleteById(ObjectId myId)
    {
        userRepository.deleteById(myId);
    }

    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }
}
