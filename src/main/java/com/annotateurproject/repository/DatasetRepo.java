package com.annotateurproject.repository;

import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasetRepo extends JpaRepository<Dataset, Integer> {

}
