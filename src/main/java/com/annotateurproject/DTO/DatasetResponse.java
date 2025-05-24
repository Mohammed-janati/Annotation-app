package com.annotateurproject.DTO;

import com.annotateurproject.entity.possibleClass;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DatasetResponse {

    int id;

    String name;
    String description;
    List<possibleClass> classes;
}
