package com.example.jpa.controller;
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

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Tag;
import com.example.jpa.repository.TagRepository;

@RestController
public class TagController {
	@Autowired
	private TagRepository tagRepository;
	@GetMapping("/tags")
	public Page<Tag> getTags(Pageable pageable) {
		return tagRepository.findAll(pageable);
	}
	@PostMapping("/tags")
	public Tag createTag(@Valid @RequestBody Tag tag) {
		return tagRepository.save(tag);
	}
	@PutMapping("/tags/{tagId}")
	public Tag updateTag(@PathVariable Long tagId, 
			@Valid @RequestBody Tag tagRequest) {
		return tagRepository.findById(tagId).map(tag -> {
			tag.setTitle(tagRequest.getTitle());
			tag.setDescription(tagRequest.getDescription());
			return tagRepository.save(tag);
		}).orElseThrow(() -> new ResourceNotFoundException("tag not found with id " + tagId));
	}
	@DeleteMapping("/tags/{tagId}")
	public ResponseEntity<?> deleteTag(@PathVariable Long tagId) {
		return tagRepository.findById(tagId).map(tag -> {
			tagRepository.delete(tag);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Tag not found with id " + tagId));
	}
}