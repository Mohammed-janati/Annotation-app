package com.annotateurproject.services;

import com.annotateurproject.DTO.anotationRequest;
import com.annotateurproject.DTO.tacheResponse;
import com.annotateurproject.entity.annotation;
import com.annotateurproject.entity.coupleTexte;
import com.annotateurproject.entity.tache;
import com.annotateurproject.mapper.customMapper;
import com.annotateurproject.repository.annotatorRepo;
import com.annotateurproject.repository.anotationRepo;
import com.annotateurproject.repository.coupleTexteRepo;
import com.annotateurproject.repository.tachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userServiceImpl implements userService {

    @Autowired
    tachRepo tacheRepo;
    @Autowired
    coupleTexteRepo texteRepo;
    @Autowired
    annotatorRepo annotatorRepo;

    @Autowired
    anotationRepo anotationRepo;


    @Autowired
    customMapper mapper;
    @Override
    public List<tacheResponse> getTaches(int id) {
        List<tacheResponse> a=new ArrayList<tacheResponse>();
        String datasetName;
      List<tache> x= tacheRepo.findByAnnotatorId(id);
      return x.stream().map(c->{
          System.out.println(c.getDataset().getName());

          var w= mapper.tacheTOTacheResponse(c, c.getDataset().getName());
          w.setCount(this.gettacheCount(c.getId()));
          w.setAvencement(this.gettacheAvancement(c.getId()));
          return w;
      }).toList();





    }

    @Override
    public Page<coupleTexte> getNextTexte(int id, int page, int size) {
        Pageable p= PageRequest.of(page, size);
        return texteRepo.findByTacheId(id,p);
    }

    @Override
    public void saveAnotation(anotationRequest anotationRequest) {
        var annotator = annotatorRepo.findById(anotationRequest.getAnnotatorId()).orElse(null);
        var annotation = anotationRepo.findByCoupleTexteId(anotationRequest.getCoupleId());

        if (annotation != null) {
            annotation.setAnnotator(annotator);

            annotation.setChosenClass(anotationRequest.getChosenclass());
            anotationRepo.save(annotation);
        } else {
            var coupletexte = texteRepo.findById(anotationRequest.getCoupleId()).orElse(null);

            annotation a = new annotation();
            a.setAnnotator(annotator);
            a.setCoupleTexte(coupletexte);
            a.setChosenClass(anotationRequest.getChosenclass());
            anotationRepo.save(a);
        }
    }

    @Override
    public int gettacheCount(int id) {
       return texteRepo.countByTache_Id(id);
    }

    @Override
    public int gettacheAvancement(int id) {
        List<Integer> texteId=texteRepo.findcoupleTexteIdByTacheId(id);
        int count=anotationRepo.countByCoupleTexte_IdIn(texteId);
        return count;
    }



}
