package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test_name", "test_url");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_name", "test_desc", "1", "2");
        Mail mail = new Mail("to", "test_subject", "test_message", "toCc");

        Mockito.when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        Mockito.doNothing().when(emailService).send(mail, MailCreatorService.NEW_TRELLO_CARD_MAIL);

        //When
        CreatedTrelloCardDto returnedCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(returnedCreatedTrelloCardDto);
        assertEquals("1", returnedCreatedTrelloCardDto.getId());
        assertEquals("test_name", returnedCreatedTrelloCardDto.getName());
        assertEquals("test_url", returnedCreatedTrelloCardDto.getShortUrl());
    }

}
