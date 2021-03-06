package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.sheduler.EmailSchedulerV2;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";
    private static final String CC = "adam.x.sikora@gmail.com";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private EmailSchedulerV2 emailSchedulerV2;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    // Zmiana w Module_24.3
    public CreatedTrelloCardDto createTrelloCard (TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        final String tpl = "New card \"%s\" has been created on your Trello account";
        ofNullable(newCard).ifPresent(
                //for template avaiable-tasks-count-mail.html
                //card -> emailSchedulerV2.sendInformationEmail()

               card -> emailService.send(new Mail(adminConfig.getAdminMail(), CC, SUBJECT,
                       "New card " + card.getName() + " has been created"), MailCreatorService.NEW_TRELLO_CARD_MAIL)
        );
        return newCard;
    }
}