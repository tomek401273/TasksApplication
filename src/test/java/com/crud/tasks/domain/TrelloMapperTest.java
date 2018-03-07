package com.crud.tasks.domain;

import com.crud.tasks.controller.TrelloController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TrelloMapperTest {

//    @Autowired
//    private TrelloMapper trelloMapper;

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToTrelloListTest() {
        // Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "one", false));
        trelloListDtos.add(new TrelloListDto("2", "two", true));
        // When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(mappedList.size(), trelloListDtos.size());
        Assert.assertEquals(mappedList.get(0).getId(), "1");
        Assert.assertEquals(mappedList.get(1).getId(), "2");
        Assert.assertEquals(mappedList.get(0).getName(), "one");
        Assert.assertEquals(mappedList.get(1).getName(), "two");
        Assert.assertEquals(mappedList.get(0).isClosed(), false);
        Assert.assertEquals(mappedList.get(1).isClosed(), true);
    }

    @Test
    public void mapToTrelloListTest2() {
        // Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        // When
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(mappedList.size(), trelloListDtos.size());
    }

    @Test
    public void mapToTrelloListDto() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "one", false));
        trelloLists.add(new TrelloList("2", "two", true));
        // When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);
        // Then
        Assert.assertEquals(mappedList.get(0).getId(), "1");
        Assert.assertEquals(mappedList.get(1).getId(), "2");
        Assert.assertEquals(mappedList.get(0).getName(), "one");
        Assert.assertEquals(mappedList.get(1).getName(), "two");
        Assert.assertEquals(mappedList.get(0).isClosed(), false);
        Assert.assertEquals(mappedList.get(1).isClosed(), true);
    }

    @Test
    public void mapToTrelloListDto2() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        // When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloLists);
        // Then
        Assert.assertEquals(mappedList.size(), trelloLists.size());
    }

    @Test
    public void mapToBoardsTest() {
        // Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "one", false));
        trelloListDtos.add(new TrelloListDto("2", "two", true));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("12", "oneBoard", trelloListDtos);
        trelloBoardDtos.add(trelloBoardDto);
        // When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(trelloBoardDtos);
        // Then
        Assert.assertEquals(mappedList.size(), 1);
        Assert.assertEquals(mappedList.get(0).getId(), "12");
        Assert.assertEquals(mappedList.get(0).getName(), "oneBoard");
        List<TrelloList> trelloLists = mappedList.get(0).getLists();
        Assert.assertEquals(trelloLists.get(0).getId(), "1");
        Assert.assertEquals(trelloLists.get(0).getName(), "one");
        Assert.assertEquals(trelloLists.get(0).isClosed(), false);
        Assert.assertEquals(trelloLists.get(1).getId(), "2");
        Assert.assertEquals(trelloLists.get(1).getName(), "two");
        Assert.assertEquals(trelloLists.get(1).isClosed(), true);
    }

    @Test
    public void mapToBoardsTest2() {
        // Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        // When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(trelloBoardDtos);
        // Then
        Assert.assertEquals(mappedList.size(), 0);
    }

    @Test
    public void mapToBoardsDtoTest() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "one", false));
        trelloLists.add(new TrelloList("2", "two", true));
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("23", "trelloBoard23", trelloLists);
        trelloBoardList.add(trelloBoard);
        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        // Then
        Assert.assertEquals(trelloBoardDtoList.size(), 1);
        Assert.assertEquals(trelloBoardDtoList.get(0).getId(), "23");
        Assert.assertEquals(trelloBoardDtoList.get(0).getName(), "trelloBoard23");
        List<TrelloListDto> mappedList = trelloBoardDtoList.get(0).getLists();
        Assert.assertEquals(mappedList.get(0).getId(), "1");
        Assert.assertEquals(mappedList.get(1).getId(), "2");
        Assert.assertEquals(mappedList.get(0).getName(), "one");
        Assert.assertEquals(mappedList.get(1).getName(), "two");
        Assert.assertEquals(mappedList.get(0).isClosed(), false);
        Assert.assertEquals(mappedList.get(1).isClosed(), true);
    }

    @Test
    public void mapToBoardsDtoTest2() {
        // Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        // Then
        Assert.assertEquals(trelloBoardDtoList.size(), 0);
    }

    @Test
    public void mapToCardDtoTest() {
        // Given
        TrelloCard trelloCard = new TrelloCard("card", "cardDesc", "1", "1");
        // When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);
        // Then
        Assert.assertEquals(mappedCard.getName(), "card");
        Assert.assertEquals(mappedCard.getDescription(), "cardDesc");
        Assert.assertEquals(mappedCard.getPos(), "1");
        Assert.assertEquals(mappedCard.getListId(), "1");
    }

    @Test
    public void mapToCardDtoTest2() {
        // Given
        TrelloCard trelloCard = new TrelloCard();
        // When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);
        // Then
        Assert.assertEquals(mappedCard.getName(), null);
        Assert.assertEquals(mappedCard.getDescription(), null);
        Assert.assertEquals(mappedCard.getPos(), null);
        Assert.assertEquals(mappedCard.getListId(), null);
    }

    @Test
    public void mapToCardTest() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "cardDesc", "1", "1");
        // When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);
        // Then
        Assert.assertEquals(mappedCard.getName(), "card");
        Assert.assertEquals(mappedCard.getDescription(), "cardDesc");
        Assert.assertEquals(mappedCard.getPos(), "1");
        Assert.assertEquals(mappedCard.getListId(), "1");
    }

    @Test
    public void mapToCardTest2() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        // When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);
        // Then
        Assert.assertEquals(mappedCard.getName(), null);
        Assert.assertEquals(mappedCard.getDescription(), null);
        Assert.assertEquals(mappedCard.getPos(), null);
        Assert.assertEquals(mappedCard.getListId(), null);
    }
}
