package com.annotateurproject.repository;

import com.annotateurproject.entity.annotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface anotationRepo extends JpaRepository<annotation, Integer> {
    annotation findByCoupleTexteId(int coupleId);

    @Query("select count(c) from annotation c where c.coupleTexte.id in :texteId")
    int countByCoupleTexte_IdIn(List<Integer> texteId);


}
