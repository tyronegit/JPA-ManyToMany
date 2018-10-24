package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

}



