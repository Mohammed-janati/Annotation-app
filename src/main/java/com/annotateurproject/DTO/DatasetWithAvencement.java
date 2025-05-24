package com.annotateurproject.DTO;

import com.annotateurproject.entity.Dataset;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DatasetWithAvencement {

    Dataset dataset;
    int avencement;
}
