package com.crud.tasks.sheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @Mock
    SimpleEmailService simpleEmailService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    AdminConfig adminConfig;

    @InjectMocks
    EmailScheduler emailScheduler;

    @Captor
    ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    public void sendInformationEmail_singleRecordCount() {

        //Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("mockAdminMail");

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail captoredMail = mailArgumentCaptor.getValue();
        assertThat(captoredMail.getMailTo()).isEqualTo("mockAdminMail");
        assertThat(captoredMail.getToCc()).isEqualTo("adam.x.sikora@gmail.com");
        assertThat(captoredMail.getSubject()).isEqualTo("Tasks: Once a day email");
        assertThat(captoredMail.getMessage()).isEqualTo("Currently in database you got: 1task");


    }

    @Test
    public void sendInformationEmail_manyRecordsCount() {

        //Given
        when(taskRepository.count()).thenReturn(123L);
        when(adminConfig.getAdminMail()).thenReturn("mockAdminMail");

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailArgumentCaptor.capture());
        Mail captoredMail = mailArgumentCaptor.getValue();
        assertThat(captoredMail.getMailTo()).isEqualTo("mockAdminMail");
        assertThat(captoredMail.getToCc()).isEqualTo("adam.x.sikora@gmail.com");
        assertThat(captoredMail.getSubject()).isEqualTo("Tasks: Once a day email");
        assertThat(captoredMail.getMessage()).isEqualTo("Currently in database you got: 123tasks");


    }

}