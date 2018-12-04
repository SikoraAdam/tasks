package com.crud.tasks.sheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailSchedulerV2 {

    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String CC = "adam.x.sikora@gmail.com";
    private static final int ONE_TASK = 1;
    private static final String SINGLE_TASK = " task";
    private static final String MULTIPLE_TASK = " tasks";

    private SimpleEmailService emailService;
    private TaskRepository taskRepository;
    private AdminConfig adminConfig;

    /**
     *  zakomentowane żeby nie chodziło w nieskończoność
     */
    // Zmiana w Module_24.3
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                CC,
                SUBJECT,
                "Currently in database you got: " + size + (size == ONE_TASK ? SINGLE_TASK : MULTIPLE_TASK)), MailCreatorService.AVAILABLE_TASKS_MAIL);
    }
}