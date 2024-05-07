package com.example.Controller.DB;

import com.example.Entity.DB.Post;
import com.example.Service.DB.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value="/save-post")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post p = postService.savePost(post);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping(value="/getall-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value="/getone-post/{id}")
    public ResponseEntity<Post> getOnePost(@PathVariable long id) {
        Post p = postService.getPostById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    @PutMapping(value="/update-post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post post) {
        Post p = postService.updatePost(post, id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}