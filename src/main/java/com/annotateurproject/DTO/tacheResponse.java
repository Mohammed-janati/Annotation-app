package com.annotateurproject.DTO;

import com.annotateurproject.entity.tache;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
public class tacheResponse {
    tache taches;
   String datasetname;
   int count;
   int avencement;
}
