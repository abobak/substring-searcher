package com.substringsearcher.app.mapper;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Task fromNewTaskDto(NewTaskPayloadDto dto);

}
