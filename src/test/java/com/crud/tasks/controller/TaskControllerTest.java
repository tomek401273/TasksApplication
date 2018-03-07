package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetAllTasks() throws Exception {
        // Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto((long) 1, "task1", "desc");
        taskDtoList.add(taskDto);
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);
        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("task1")))
                .andExpect(jsonPath("$[0].content", is("desc")));
    }
//    @Test
//    public void shouldGetSingleTask() throws Exception {
//        // Given
//        TaskDto taskDto = new TaskDto((long) 1, "task1", "desc");
//        Task task = new Task();
//        when(dbService.getTaskById((long)1).orElseThrow(TaskNotFoundException::new)).thenReturn(task);
//
////        mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//
//    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // Given
        when(dbService.deleteTask((long) 1)).thenReturn(true);
        //When & Then
        Gson gson = new Gson();
        String jsonContent = gson.toJson((long) 1);

        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", jsonContent)
                .content(jsonContent))
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto((long) 1, "task1", "desc");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.content", is("desc")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto((long) 1, "task1", "desc");
        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("task1")))
                .andExpect(jsonPath("$.content",is("desc")));
    }
}
