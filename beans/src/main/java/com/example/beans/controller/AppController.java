package com.example.beans.controller;

import com.example.beans.model.Post;
import com.example.beans.model.User;
import com.example.beans.service.PostService;
import com.example.beans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AppController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/users"})
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.findById(id);
        return user != null ? new ResponseEntity<>(user,HttpStatus.OK)
                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    d}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post post = postService.findById(id);
        return post != null ? new ResponseEntity<>(post, HttpStatus.OK)
                            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) throws Exception {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping(value = {"/users/{id}"})
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        User updatedUser = userService.update(user);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK)
                                   : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = {"/posts/{id}"})
    public ResponseEntity<Post> updatePost(@RequestBody Post post) throws Exception {
        Post updatedPost = postService.save(post);
        return updatedPost != null ? new ResponseEntity<>(updatedPost, HttpStatus.OK)
                                   : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = {"/users/{id}"})
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable long id) throws Exception {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = {"/posts/{id}"})
    public ResponseEntity<HttpStatus> deletePostById(@PathVariable long id) throws Exception {
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
