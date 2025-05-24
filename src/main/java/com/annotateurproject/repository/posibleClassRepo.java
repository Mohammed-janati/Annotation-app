package com.annotateurproject.repository;

import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.possibleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface posibleClassRepo extends JpaRepository<possibleClass,Integer> {

}
