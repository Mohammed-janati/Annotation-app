package com.annotateurproject.services;

import com.annotateurproject.DTO.anotationRequest;
import com.annotateurproject.DTO.tacheResponse;
import com.annotateurproject.entity.coupleTexte;
import com.annotateurproject.entity.tache;
import org.springframework.data.domain.Page;

import java.util.List;

public interface userService {
   List<tacheResponse> getTaches(int id);



    Page<coupleTexte> getNextTexte(int id, int page, int size);

    void saveAnotation(anotationRequest anotationRequest);

    int gettacheCount(int id);

    int gettacheAvancement(int id);
}
