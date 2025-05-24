package com.annotateurproject.controller;


import com.annotateurproject.DTO.DatasetRequest;
import com.annotateurproject.DTO.DatasetResponse;
import com.annotateurproject.DTO.DatasetWithAvencement;
import com.annotateurproject.DTO.annotatorRequest;
import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.annotator;
import com.annotateurproject.entity.coupleTexte;
import com.annotateurproject.services.adminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    adminServiceImpl adminservice;


    @PostMapping("/createDataset")
    public ResponseEntity<String> createDataset(@RequestParam("file") MultipartFile file,
                                                @RequestParam("name") String name,
                                                @RequestParam("className") String className,
                                                @RequestParam("description") String description) throws IOException {

        adminservice.createDataset(file,name,className,description);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/affect")
    public ResponseEntity<String> affectDataset(@RequestBody annotatorRequest annotators) throws IOException {
    adminservice.affecteAnnotator(annotators.getAnnotators(), annotators.getDataset());
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allDataset")
    public ResponseEntity<List<DatasetWithAvencement>> showDataset()  {
var x=adminservice.showDataset();
        x.forEach(System.out::println);
        return ResponseEntity.status(HttpStatus.OK).body(x);
    }

    @GetMapping("/allAnnotator")
    public ResponseEntity<List<annotator>> showannotator()  {
        var x=adminservice.showAnnotator();

        return ResponseEntity.status(HttpStatus.OK).body(x);
    }


    @GetMapping("/dataset/{id}")
        public ResponseEntity<DatasetResponse> getDataset(@PathVariable int id)  {
        var x=adminservice.getDataset(id);

        return ResponseEntity.status(HttpStatus.OK).body(x);
    }


    @GetMapping("/coupleText/{id}/{page}/{size}")
    public ResponseEntity<Page<coupleTexte>> showCoupleText(@PathVariable int id,
                                                            @PathVariable int page ,
                                                            @PathVariable int size)  {
        var x=adminservice.getPaginatedTexte(id,page,size);
        return ResponseEntity.status(HttpStatus.OK).body(x);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataset(@PathVariable int id) {
        adminservice.deleteAnotator(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("getAnnotatorByid/{id}")
    public ResponseEntity<annotator> getAnotatorById(@PathVariable int id) {
       var x= adminservice.getAnnotatorById(id);
        return ResponseEntity.ok(x);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyAnnot(@RequestBody annotator a) {
        adminservice.modifyAnotator(a);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/datasetCount/{id}")
    public int getDatasetCount(@PathVariable int id)  {
        return adminservice.getDatasetCount(id);
    }
    @GetMapping("/DatasetAvancement/{id}")
    public int getDatasetAvancement(@PathVariable int id)  {
        return adminservice.getDatasetAvancement(id);
    }

    @GetMapping("/export/{id}")
    public ResponseEntity<Resource> exportUsers(@PathVariable int id ) throws IOException {

var resource=adminservice.getRessource(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv")
                .contentType(MediaType.parseMediaType("text/csv"))

                .body(resource);
    }




    @GetMapping("/annotatorPerDataset/{id}")
    public ResponseEntity<List<annotator>> getAnnotatorPerDataset(@PathVariable int id) {
        var x=adminservice.getAnnotatorPerDataset(id);
      return  ResponseEntity.status(HttpStatus.OK).body(x);
    }
    @DeleteMapping("deleteTache/{id}")
    public ResponseEntity<String> deleteTache(@PathVariable int id) {
        adminservice.deleteTache(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
