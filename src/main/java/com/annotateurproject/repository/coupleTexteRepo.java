package com.annotateurproject.repository;

import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.coupleTexte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface coupleTexteRepo extends JpaRepository<coupleTexte, Integer> {
    List<coupleTexte> findAllByDataset(Dataset d);

    Page<coupleTexte> findByDatasetId(int id, Pageable p);
    List<coupleTexte> findByDatasetId(int id);

    Page<coupleTexte> findByTacheId(int id, Pageable p);

    @Query("SELECT c.id FROM coupleTexte c WHERE c.tache.id = :id")
    List<Integer> findcoupleTexteIdByTacheId(int id);

    Integer countByDataset_Id(int id);

    int countByTache_Id(int id);
    @Query("SELECT c.id FROM coupleTexte c WHERE c.dataset.id = :id")
    List<Integer> findcoupleTexteIdByDatasetId(int id);
}
