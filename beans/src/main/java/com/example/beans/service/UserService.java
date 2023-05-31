package com.example.beans.service;

import com.example.beans.conditions.AppLogger;
import com.example.beans.conditions.JsonParser;
import com.example.beans.model.User;
import com.example.beans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private AppLogger logger;
    @Autowired
    private JsonParser parser;
    public List<User> findAll(){
        logger.getLogger();
        System.out.println(parser.getJson());
        return repository.findAll();
    }
    public User save(User user) throws Exception {
        if (!StringUtils.hasText(user.getName())) {
            throw new Exception("Name is required");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new Exception("Email is required");
        }
        return repository.save(user);
    }
    public User update(User user) throws Exception {
        if (!StringUtils.hasText(user.getName())) {
            throw new Exception("Name is required");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new Exception("Email is required");
        }

        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public void deleteById(long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Cannot find User with id: " + id);
        }
        else {
            repository.deleteById(id);
        }
    }
}
