package com.annotateurproject.repository;

import com.annotateurproject.entity.annotator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface annotatorRepo extends JpaRepository<annotator, Integer> {


    List<annotator> findAllByNotActive(boolean b);
}
