package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.thymeleaf.TemplateEngine;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class MailCreatorServiceTest {

    @Mock
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TaskRepository taskRepository;

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

    }
}
