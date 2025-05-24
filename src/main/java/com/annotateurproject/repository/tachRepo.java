package com.annotateurproject.repository;

import com.annotateurproject.entity.annotator;
import com.annotateurproject.entity.tache;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface tachRepo extends JpaRepository<tache, Integer> {

    @Query("SELECT t.annotator FROM tache t WHERE t.dataset.id = :id")
    List<annotator> findAllByDatasetId(int id);

    @Transactional
    @Modifying
    @Query("delete  FROM tache t WHERE t.annotator.id = :id")
    void deleteByAnnotatorId(@Param("id") int id);

@Query("select t from tache t where t.annotator.id=:id")
    List<tache> findByAnnotatorId(int id);

    @Query("select t.dataset.name from tache t where t.annotator.id=:id")
    List<String> findDatasetName(int id);

}
