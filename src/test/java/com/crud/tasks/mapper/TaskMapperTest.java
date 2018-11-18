package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTaskTest() {

        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(1L, task.getId().longValue());
        Assert.assertEquals("title", task.getTitle());
        Assert.assertEquals("content", task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {

        //Given
        Task task = new Task(1L, "title", "desc");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(1L, taskDto.getId().longValue());
        Assert.assertEquals("title", taskDto.getTitle());
        Assert.assertEquals("desc", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {

        //Given
        Task task1 = new Task(1L, "title", "desc");
        Task task2 = new Task(2L, "title", "desc");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(2, taskDtoList.size());
    }
}
