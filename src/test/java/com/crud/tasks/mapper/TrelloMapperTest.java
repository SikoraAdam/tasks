package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    @Test
    public void mapToBoardTest(){

        //Given
        TrelloList trelloList1 = new TrelloList("id_list1", "name_list", true);
        TrelloList trelloList2 = new TrelloList("id_list2", "name_list", true);
        List<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto();
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtosList = new ArrayList<>();
        trelloBoardDtosList.add(trelloBoardDto1);
        trelloBoardDtosList.add(trelloBoardDto2);

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoard> trelloBoardsList = trelloMapper.mapToBoards(trelloBoardDtosList);

        //Then
        Assert.assertEquals(2, trelloBoardsList.size());
    }

    @Test
    public void mapToBoardTestDto(){

        //Given
        TrelloList trelloList1 = new TrelloList("id_list_1", "name_list", true);
        TrelloList trelloList2 = new TrelloList("id_list_2", "name_list", true);
        List<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("id_board_1", "name_board", trelloListsList);
        TrelloBoard trelloBoard2 = new TrelloBoard("id_board_2", "name_board", trelloListsList);
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(trelloBoard1);
        trelloBoardsList.add(trelloBoard2);

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoardDto> trelloBoardList = trelloMapper.mapToBoardsDto(trelloBoardsList);

        //Then
        Assert.assertEquals(2, trelloBoardList.size());
    }

    @Test
    public void mapToListTest(){

        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto();
        TrelloListDto trelloListDto2 = new TrelloListDto();
        List<TrelloListDto> trelloListDtosList = new ArrayList<>();
        trelloListDtosList.add(trelloListDto1);
        trelloListDtosList.add(trelloListDto2);

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtosList);

        //Then
        Assert.assertEquals(2, trelloListList.size());
    }

    @Test
    public void mapToListTestDto(){

        //Given
        TrelloList trelloList1 = new TrelloList("id_list1", "name_list", true);
        TrelloList trelloList2 = new TrelloList("id_list2", "name_list", true);
        List<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloListDto> trelloListDtosList = trelloMapper.mapToListDto(trelloListsList);

        //Then
        Assert.assertEquals(2, trelloListDtosList.size());
    }

    @Test
    public void mapToCardTest(){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name_card", "description", "pos", "listId");

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("name_card", trelloCard.getName());
        Assert.assertEquals("description", trelloCard.getDescription());
        Assert.assertEquals("pos", trelloCard.getPos());
        Assert.assertEquals("listId", trelloCard.getListId());
    }

    @Test
    public void mapToCardTestDto(){

        //Given
        TrelloCard trelloCard = new TrelloCard("name_card", "desc_card", "pos_card", "listId_card");

        //When
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("name_card", trelloCardDto.getName());
        Assert.assertEquals("desc_card", trelloCardDto.getDescription());
        Assert.assertEquals("pos_card", trelloCardDto.getPos());
        Assert.assertEquals("listId_card", trelloCardDto.getListId());
    }
}
