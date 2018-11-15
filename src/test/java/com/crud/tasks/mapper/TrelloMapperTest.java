package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();


    @Test
    public void mapToBoardTest(){

         //Given
        TrelloListDto trelloListDto1 = new TrelloListDto();
        TrelloListDto trelloListDto2 = new TrelloListDto();
        List<TrelloListDto> trelloListDtosList = new ArrayList<>();
        trelloListDtosList.add(trelloListDto1);
        trelloListDtosList.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("id_board_1", "name_board_1", trelloListDtosList);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("id_board_2", "name_board_2", trelloListDtosList);
        List<TrelloBoardDto> trelloBoardDtosList = new ArrayList<>();
        trelloBoardDtosList.add(trelloBoardDto1);
        trelloBoardDtosList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoardsList = trelloMapper.mapToBoards(trelloBoardDtosList);

        //Then
        Assert.assertEquals(2, trelloBoardsList.size());
        Assert.assertEquals("id_board_1", trelloBoardsList.get(0).getId());
        Assert.assertEquals("name_board_2", trelloBoardsList.get(1).getName());
    }

    @Test
    public void mapToBoardTestDto(){

        //Given
        TrelloList trelloList1 = new TrelloList("id_list_1", "name_list", true);
        TrelloList trelloList2 = new TrelloList("id_list_2", "name_list", true);
        List<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);

        TrelloBoard trelloBoard1 = new TrelloBoard("id_1", "name_1", trelloListsList);
        TrelloBoard trelloBoard2 = new TrelloBoard("id_2", "name_2", trelloListsList);
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        trelloBoardsList.add(trelloBoard1);
        trelloBoardsList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardList = trelloMapper.mapToBoardsDto(trelloBoardsList);

        //Then
        Assert.assertEquals(2, trelloBoardList.size());
        Assert.assertEquals("id_1", trelloBoardList.get(0).getId());
        Assert.assertEquals("name_2", trelloBoardList.get(1).getName());
    }

    @Test
    public void mapToListTest(){

        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("id_list_1", "name_list_1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id_list_2", "name_list_2", true);
        List<TrelloListDto> trelloListDtosList = new ArrayList<>();
        trelloListDtosList.add(trelloListDto1);
        trelloListDtosList.add(trelloListDto2);

        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtosList);

        //Then
        Assert.assertEquals(2, trelloListList.size());
        Assert.assertEquals("id_list_1", trelloListList.get(0).getId());
        Assert.assertEquals("name_list_2", trelloListList.get(1).getName());
    }

    @Test
    public void mapToListTestDto(){

        //Given
        TrelloList trelloList1 = new TrelloList("id_list_1", "name_list_1", true);
        TrelloList trelloList2 = new TrelloList("id_list_2", "name_list_2", true);
        List<TrelloList> trelloListsList = new ArrayList<>();
        trelloListsList.add(trelloList1);
        trelloListsList.add(trelloList2);

        //When
        List<TrelloListDto> trelloListDtosList = trelloMapper.mapToListDto(trelloListsList);

        //Then
        Assert.assertEquals(2, trelloListDtosList.size());
        Assert.assertEquals("id_list_1", trelloListDtosList.get(0).getId());
        Assert.assertEquals("name_list_2", trelloListDtosList.get(1).getName());
    }

    @Test
    public void mapToCardTest(){

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name_card", "description", "pos", "listId");

        //When
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
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("name_card", trelloCardDto.getName());
        Assert.assertEquals("desc_card", trelloCardDto.getDescription());
        Assert.assertEquals("pos_card", trelloCardDto.getPos());
        Assert.assertEquals("listId_card", trelloCardDto.getListId());
    }
}
