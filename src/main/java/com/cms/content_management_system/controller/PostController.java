package com.cms.content_management_system.controller;

import com.cms.content_management_system.entity.Post;
import com.cms.content_management_system.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    @GetMapping("/{pId}")
    public Optional<Post> getPostById(@PathVariable Integer pId) {
        return Optional.ofNullable(postService.getPostById(pId));
    }
    @PostMapping
    public void savePost(@RequestBody Post post) {
        postService.savePost(post);
    }
    @DeleteMapping("/{pId}")
    public void deletePostById(@PathVariable Integer pId) {
        postService.deletePostById(pId);
    }
}
