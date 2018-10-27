package com.example.jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Post;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.TagRepository;



@RestController
@RequestMapping("/")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	@GetMapping("/posts")
	public Page<Post> getPosts(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	/*
	@PutMapping("/questions/{questionId}")
	public Question updateQuestion(@PathVariable Long questionId, 
			@Valid @RequestBody Question questionRequest) {
		return questionRepository.findById(questionId).map(question -> {
			question.setTitle(questionRequest.getTitle());
			question.setDescription(questionRequest.getDescription());
			return questionRepository.save(question);
		}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		return questionRepository.findById(questionId).map(question -> {
			questionRepository.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}*/
	
    }
