package com.annotateurproject.controller;

import com.annotateurproject.DTO.anotationRequest;
import com.annotateurproject.DTO.tacheResponse;
import com.annotateurproject.entity.coupleTexte;
import com.annotateurproject.entity.tache;
import com.annotateurproject.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    userService service;

    @GetMapping("/taches/{id}")
    public List<tacheResponse> taches(@PathVariable int id) {
return this.service.getTaches(id);
    }


    @GetMapping("/getNextTexte/{id}/{page}")
    public Page<coupleTexte> getNextTexte(@PathVariable int id, @PathVariable int page) {
        return service.getNextTexte(id,page,1);

    }

    @PostMapping("/annotation")
    public ResponseEntity annotation(@RequestBody anotationRequest anotationRequest) {
        service.saveAnotation(anotationRequest);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/tacheCount/{id}")
    public int gettacheCount(@PathVariable int id)  {
        return service.gettacheCount(id);
    }

    @GetMapping("/tacheAvancement/{id}")
    public int gettacheAvancement(@PathVariable int id)  {
        return service.gettacheAvancement(id);
    }


}
