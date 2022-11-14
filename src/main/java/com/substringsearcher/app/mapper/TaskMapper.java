package com.substringsearcher.app.mapper;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.dto.TaskDto;
import com.substringsearcher.app.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Task fromNewTaskDto(NewTaskPayloadDto dto);

    List<TaskDto> dtosFromTaskList(List<Task> tasks);

    TaskDto fromTask(Task task);

}
