package com.example.demo.controller;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Post>> getallpost(){
        return new ResponseEntity<List<Post>>(postRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Post> getpostbyid(@PathVariable Long id){
        return new ResponseEntity<Post>(postRepository.findById(id).get(),HttpStatus.OK);
    }
    @PostMapping(value = "/post/{id}")
    public ResponseEntity<Post> addComment(@PathVariable(value = "id") Long id , @RequestBody Comments requestComment){
        Post post = postRepository.findById(id).orElse(null);
        Comments comments = commentRepository.save(requestComment);
        post.getCommentsList().add(comments);
        return new ResponseEntity<Post> (postRepository.save(post),HttpStatus.CREATED);
    }
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<String> updateComment(@PathVariable (value = "id") Long id , @RequestBody Comments requesetComment){
        Comments comment = commentRepository.findById(id).get();
        comment.setCmt(requesetComment.getCmt());
        commentRepository.save(comment);
        return new ResponseEntity<String>("Updated",HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/comment/{id}")
    public ResponseEntity<String> DeleteComment(@PathVariable (value = "id") Long id){
        commentRepository.deleteById(id);
        return new ResponseEntity<String>("The comment was Deleted",HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/post/{id}")
    public ResponseEntity<String> DeletePost(@PathVariable (value = "id") Long id){
        postRepository.deleteById(id);
        return new ResponseEntity<>("The post was Deleted",HttpStatus.OK);
    }
}
