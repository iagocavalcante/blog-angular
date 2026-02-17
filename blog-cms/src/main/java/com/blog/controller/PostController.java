package com.blog.controller;

import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postRepository.findById(id)
            .map(existingPost -> {
                existingPost.setConteudo(post.getConteudo());
                existingPost.setTags(post.getTags());
                existingPost.setStatus(post.getStatus());
                existingPost.setDtAtualizacao(java.time.LocalDate.now());
                return ResponseEntity.ok(postRepository.save(existingPost));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
