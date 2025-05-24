package com.annotateurproject.services;

import com.annotateurproject.DTO.DatasetRequest;
import com.annotateurproject.DTO.DatasetResponse;
import com.annotateurproject.DTO.DatasetWithAvencement;
import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.annotator;

import com.annotateurproject.entity.coupleTexte;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public interface adminService {
     void createDataset(MultipartFile file, String name, String className, String description) throws IOException;
    void affecteAnnotator(List<Integer> annotators,int id );

    List<DatasetWithAvencement> showDataset();

    List<annotator> showAnnotator();

    DatasetResponse getDataset(int id);

    Page<coupleTexte> getPaginatedTexte(int id, int page, int size);

    void deleteAnotator(int id );

    annotator getAnnotatorById(int id);

    void modifyAnotator(annotator id);

    List<annotator> getAnnotatorPerDataset(int id);

    void deleteTache(int id);

    int getDatasetCount(int id);


    int getDatasetAvancement(int id);



    Resource getRessource(int id);
}
