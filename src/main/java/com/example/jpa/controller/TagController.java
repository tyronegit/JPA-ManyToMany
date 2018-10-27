package com.example.jpa.controller;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Post;
import com.example.jpa.model.Tag;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.TagRepository;

;

@RestController
public class TagController {
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	//@GetMapping("/post/{postId}/tags")
	//public List<Tag> getTagsByPostId(@PathVariable Long postId) {

		//return tagRepository.findByPostId(postId);
	//}
	@PostMapping("/questions/{questionId}/answers")
	public Tag addTag (@PathVariable Long postId, @Valid @RequestBody Tag tag) {

		return postRepository.findById(postId).map(post -> {
			tag.setPosts((Set<Post>) post);
			return tagRepository.save(tag);

		}).orElseThrow(() -> new ResourceAccessException("Question not found with id " + postId));

	}
	@PutMapping("/posts/{postId}/tags/{tqgId}")
    public Tag updateAnswer(@PathVariable Long postId,
                               @PathVariable Long tagId,
                               @Valid @RequestBody Tag tagRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Question not found with id " + postId);
        }

        return tagRepository.findById(tagId)
                .map(tag -> {
                    tag.setText(tagRequest.getText());
                    return tagRepository.save(tag);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + tagId));
    }
	
	@DeleteMapping("/tags/{tagId}")
	public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
		return tagRepository.findById(tagId).map(tag -> {
			tagRepository.delete(tag);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Tag not found with id " + tagId));
	}
}