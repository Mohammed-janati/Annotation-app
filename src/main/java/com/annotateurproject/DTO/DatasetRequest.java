package com.annotateurproject.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter @Setter
@ToString
public class DatasetRequest {

    String name;
    String className;
    String description;
    File file;
}
