package com.annotateurproject.mapper;

import com.annotateurproject.DTO.DatasetRequest;
import com.annotateurproject.DTO.DatasetResponse;
import com.annotateurproject.DTO.tacheResponse;
import com.annotateurproject.entity.Dataset;
import com.annotateurproject.entity.possibleClass;
import com.annotateurproject.entity.tache;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface customMapper {

    customMapper INSTANCE = Mappers.getMapper(customMapper.class);
    @Mapping(target = "id", ignore = true)
    Dataset DatasetRequestTODataset(DatasetRequest customer);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "className" ,target = "name")
//    possibleClass DatasetRequestTOPossibleClass(DatasetRequest customer);

DatasetResponse datasetTODatasetResponse(Dataset customer);

@Mapping(source = "name" ,target = "datasetname")
tacheResponse tacheTOTacheResponse(tache taches, String name);

}