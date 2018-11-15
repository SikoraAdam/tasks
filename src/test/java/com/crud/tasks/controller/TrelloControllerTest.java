package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    Gson gson = new Gson();

    @Test
    public void getTrelloBoardsTest() throws Exception {

        // Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test Task", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When & Then
        mockMvc.perform(get("http://localhost:8080/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Test Task")))
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));
    }

    @Test
    public void createTrelloCardTest() throws Exception {

        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("123", "Test", "url");

        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        String jsonContent = gson.toJson(trelloCardDto);

        // When & Then
        mockMvc.perform(post("http://localhost:8080/v1/trello/createTrelloCard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.shortUrl", is("url")));

    }
}