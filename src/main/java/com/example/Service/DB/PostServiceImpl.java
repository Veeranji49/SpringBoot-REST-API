package com.example.Service.DB;

import com.example.Entity.DB.Post;
import com.example.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(Post post, long id) {
        Post post1 = postRepository.findById(id).get();
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        post1.setAuthor(post.getAuthor());
        post1.setComments(post.getComments());
        return postRepository.save(post1);
    }
}
