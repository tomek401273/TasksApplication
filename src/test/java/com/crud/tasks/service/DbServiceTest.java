package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {
    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTask() {
        // Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task((long)1,"title","content");
        taskList.add(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        // When
        List<Task> taskList1= dbService.getAllTasks();
        // Then
        Long aLong = Long.valueOf(1);
        Assert.assertEquals(taskList1.size(),1);
        Assert.assertEquals(taskList1.get(0).getId(),aLong);
        Assert.assertEquals(taskList1.get(0).getTitle(), "title");
        Assert.assertEquals(taskList1.get(0).getContent(),"content");
    }

    @Test
    public void getAllTask2() {
        // Given
        List<Task> taskList = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(taskList);
        // When
        List<Task> taskList1= dbService.getAllTasks();
        // Then
        Assert.assertEquals(taskList1.size(),0);
    }

    @Test
    public void getTaskByIdTest() {
        // Given
        Task task = new Task((long)1,"title","content");
        when(taskRepository.findById((long)1)).thenReturn(java.util.Optional.ofNullable(task));
        // When
        Optional<Task> task1 = dbService.getTaskById((long)1);
        // Then
        Long aLong = Long.valueOf(1);
        Assert.assertEquals(task1.get().getId(),aLong);
        Assert.assertEquals(task1.get().getTitle(),"title");
        Assert.assertEquals(task1.get().getContent(), "content");
    }

    @Test
    public void saveTaskTest() {
        // Given
        Task task = new Task((long)1,"title","content");
        when(taskRepository.save(task)).thenReturn(task);
        Long aLong = Long.valueOf(1);
        // When
        Task task1 = dbService.saveTask(task);
        // Then
        Assert.assertEquals(task1.getId(),aLong);
        Assert.assertEquals(task1.getTitle(), "title");
        Assert.assertEquals(task1.getContent(),"content");
    }

//    @Test
//    public void delteteTask() {
//        // Given
//        Long aLong = Long.valueOf(1);
////        when(dbService.deleteTask(aLong)).thenReturn(true);
//        doThrow().when(dbService.deleteTask(aLong)).booleanValue();
//        // When
//        boolean isDeteted = dbService.deleteTask(aLong);
//        Assert.assertEquals(isDeteted,true);
//
//    }
}
