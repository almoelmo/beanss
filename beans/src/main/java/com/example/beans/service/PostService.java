package com.example.beans.service;

import com.example.beans.model.Post;
import com.example.beans.repository.PostRepository;
import com.example.beans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post save(Post post) throws Exception {
        if (!StringUtils.hasText(post.getTitle())) {
            throw new Exception("Title is required");
        }
        if (!StringUtils.hasText(post.getContent())) {
            throw new Exception("Content is required");
        }
        post.setUser(userRepository.findById(1L).get());
        return repository.save(post);
    }


    public Post findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Cannot find Post with id: " + id);
        } else {
            repository.deleteById(id);
        }
    }
}
