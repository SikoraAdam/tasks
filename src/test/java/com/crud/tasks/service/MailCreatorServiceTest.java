package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailCreatorServiceTest {

    @MockBean
    AdminConfig adminConfig;

    @MockBean
    TaskRepository taskRepository;

    @Autowired
    MailCreatorService mailCreatorService;

    @Test
    public void tasksCountEmailTest() {

        //Given
        String message = "test message";
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminName()).thenReturn("mockAdminName");
        when(adminConfig.getCompanyName()).thenReturn("mockCompanyName");
        when(adminConfig.getCompanyEmail()).thenReturn("mockCompanyEmail");
        when(adminConfig.getCompanyPhone()).thenReturn("mockCompanyPhone");
        when(adminConfig.getCompanyGoal()).thenReturn("mockCompanyGoal");

        Task task = new Task();
        task.setTitle("mockTitle");
        task.setContent("mockContent");
        task.setId(123L);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));

        //When
        String template = mailCreatorService.tasksCountEmail(message);

        //Then
        assertThat(template.contains("mockAdminName")).isTrue();
        assertThat(template.contains("mockCompanyName")).isTrue();
        assertThat(template.contains("mockCompanyEmail")).isTrue();
        assertThat(template.contains("mockCompanyPhone")).isTrue();
        assertThat(template.contains("mockCompanyGoal")).isTrue();
        assertThat(template.contains("test message")).isTrue();
    }
}
