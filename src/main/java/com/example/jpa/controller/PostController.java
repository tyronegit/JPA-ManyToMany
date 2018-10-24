package com.example.jpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Post;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.TagRepository;



@RestController
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("/tags/{tagId}/posts")
	public List<Post> getAnswersByQuestionId(@PathVariable Long tagId) {

		return postRepository.findByTagId(tagId);

	}

	
	@PostMapping("/tags/{tagId}/posts")
	public Post addPost(@PathVariable Long tagId, @Valid @RequestBody Post post) {

		return tagRepository.findById(tagId).map(tag -> {
			post.setTags(tag);
			return postRepository.save(post);

		}).orElseThrow(() -> new ResourceAccessException("Question not found with id " + tagId));

	}
	@PutMapping("/tags/{tagId}/posts/{postId}")
	public Post updatPost(@PathVariable Long tagId,
						  @PathVariable Long postId) {
		if(!tagReposiory.existsById(tagId)) {
			throw new ResourceNotFoundException("Tag not found with id " + tagId);
			
		}
		
		return postRepository.findById(postId)
				.map(post ->{
					post.setText(postRequest.getText());
					return postRepository.save(post);
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + postId))
	}
	
	@DeleteMapping("/posts/{postId}/posts/{postId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long tagId,
                                          @PathVariable Long postId) {
        if(!postRepository.existsById(tagId)) {
            throw new ResourceNotFoundException("Question not found with id " + tagId);
        }
        
        return postRepository.findById(postId)
                .map(answer -> {
                    postRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + postId));

    }
