package com.crud.tasks.domain;

import com.crud.tasks.mapper.TaskMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTest {
    @Test
    public void mapToTaskTest() {
        // Given
        TaskDto taskDto = new TaskDto((long)1,"title","content");
        TaskMapper taskMapper = new TaskMapper();
        // When
        Task task = taskMapper.mapToTask(taskDto);
        Long aLong = Long.valueOf(1);
        // Then
        Assert.assertEquals(task.getId(),aLong);
        Assert.assertEquals(task.getTitle(), "title");
        Assert.assertEquals(task.getContent(), "content");
    }
    @Test
    public void mapToTaskTest2() {
        // Given
        TaskDto taskDto = new TaskDto();
        TaskMapper taskMapper = new TaskMapper();
        // When
        Task task = taskMapper.mapToTask(taskDto);
        // Then
        Assert.assertEquals(task.getId(),null);
        Assert.assertEquals(task.getTitle(), null);
        Assert.assertEquals(task.getContent(), null);
    }
    @Test
    public void mapToTaskDto() {
        // Given
        Task task = new Task((long)1,"title","content");
        TaskMapper taskMapper = new TaskMapper();
        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // Then
        Long aLong = Long.valueOf(1);
        Assert.assertEquals(taskDto.getId(), aLong);
        Assert.assertEquals(taskDto.getTitle(),"title");
        Assert.assertEquals(taskDto.getContent(),"content");
    }
    @Test
    public void mapToTaskDto2() {
        // Given
        Task task = new Task();
        TaskMapper taskMapper = new TaskMapper();
        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        // Then
        Assert.assertEquals(taskDto.getId(), null);
        Assert.assertEquals(taskDto.getTitle(),null);
        Assert.assertEquals(taskDto.getContent(),null);
    }

    @Test
    public void mapToTaskDtoList() {
        // Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task((long)1,"title","content");
        taskList.add(task);
        TaskMapper taskMapper = new TaskMapper();
        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        // Then
        Long aLong = Long.valueOf(1);
        Assert.assertEquals(taskDtoList.size(), 1);
        Assert.assertEquals(taskDtoList.get(0).getId(), aLong);
        Assert.assertEquals(taskDtoList.get(0).getContent(), "content");
        Assert.assertEquals(taskDtoList.get(0).getTitle(),"title");
    }
    @Test
    public void mapToTaskDtoList2() {
        // Given
        List<Task> taskList = new ArrayList<>();
        TaskMapper taskMapper = new TaskMapper();
        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        // Then
        Assert.assertEquals(taskDtoList.size(), 0);
    }
}
