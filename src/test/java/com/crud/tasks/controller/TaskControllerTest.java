package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    Gson gson = new Gson();

    @Test
    public void getTasksTest() throws Exception {
        // Given
        Task task = new Task(Long.MAX_VALUE, "Test Title", "Test Content");
        List<Task> tasks = Arrays.asList(task);
        TaskDto taskDto = new TaskDto(Long.MAX_VALUE, "Test Title", "Test Content");
        List<TaskDto> taskDtos = Arrays.asList(taskDto);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        // When & Then
        mockMvc.perform(get("http://localhost:8080/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(Long.valueOf(Long.MAX_VALUE))))
                .andExpect(jsonPath("$[0].title", is("Test Title")))
                .andExpect(jsonPath("$[0].content", is("Test Content")));
    }

    @Test
    public void getTaskByIdTest() throws Exception {
        // Given
        Task task = new Task(Long.MAX_VALUE, "Test Title", "Test Content");
        TaskDto taskDto = new TaskDto(Long.MAX_VALUE, "Test Title", "Test Content");

        when(service.getTask(Long.MAX_VALUE)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("http://localhost:8080/v1/task/getTask?taskId=" + Long.MAX_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Long.valueOf(Long.MAX_VALUE))))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }

    @Test
    public void deleteTaskByIdTest() throws Exception {
        // Given
        when(service.taskExist(Long.MAX_VALUE)).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("http://localhost:8080/v1/task/deleteTask?taskId=" + Long.MAX_VALUE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteTask(Long.MAX_VALUE);
    }

    @Test
    public void createTaskTest() throws Exception {

        // Given
        Task task = new Task(Long.MAX_VALUE, "Test Title", "Test Content");
        TaskDto taskDto = new TaskDto(Long.MAX_VALUE, "Test Title", "Test Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        String jsonContent = gson.toJson(taskDto);


        // When & Then
        mockMvc.perform(post("http://localhost:8080/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(taskMapper, times(1)).mapToTask(taskDto);
        verify(service, times(1)).saveTask(task);
    }

    @Test
    public void updateTaskTest() throws Exception {
        // Given
        Task task = new Task(Long.MAX_VALUE, "Test Title", "Test Content");
        TaskDto taskDto = new TaskDto(Long.MAX_VALUE, "Test Title", "Test Content");

        when(service.taskExist(Long.MAX_VALUE)).thenReturn(true);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("http://localhost:8080/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Long.valueOf(Long.MAX_VALUE))))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }
}