package com.example.demo;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
    @Autowired
    private PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {
        Post post = new Post("theara","thearaholishit");
        Comments comments1= new Comments("hello");
        Comments comments2= new Comments("hello girl");
        Comments comments3= new Comments("hello man");
        Comments comments4= new Comments("hello kitty");
        Comments comments5= new Comments("hello holishit");
        post.getCommentsList().add(comments1);
        post.getCommentsList().add(comments2);
        post.getCommentsList().add(comments3);
        post.getCommentsList().add(comments4);
        post.getCommentsList().add(comments5);
        this.postRepository.save(post);
    }
}
