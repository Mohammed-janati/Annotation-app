package com.annotateurproject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class annotatorRequest {
    private List<Integer> annotators;
    int dataset;

}
