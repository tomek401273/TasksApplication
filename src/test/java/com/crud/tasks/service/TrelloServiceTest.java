package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "Test List", false));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "Test Task", trelloListDtos));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        // When
        List<TrelloBoardDto> trelloBoardDtos2 = trelloService.fetchTrelloBoards();
        // Then
        Assert.assertEquals(trelloBoardDtos2.size(), 1);
        Assert.assertEquals(trelloBoardDtos2.get(0).getId(), "1");
        Assert.assertEquals(trelloBoardDtos2.get(0).getName(), "Test Task");
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().size(), 1);
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).getId(), "1");
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).getName(), "Test List");
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).isClosed(), false);
    }

    @Test
    public void fetchTrelloBoards2() {
        // Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto(null, null, false));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto(null, null, trelloListDtos));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        // When
        List<TrelloBoardDto> trelloBoardDtos2 = trelloService.fetchTrelloBoards();
        // Then
        Assert.assertEquals(trelloBoardDtos2.size(), 1);
        Assert.assertEquals(trelloBoardDtos2.get(0).getId(), null);
        Assert.assertEquals(trelloBoardDtos2.get(0).getName(), null);
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().size(), 1);
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).getId(), null);
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).getName(), null);
        Assert.assertEquals(trelloBoardDtos2.get(0).getLists().get(0).isClosed(), false);
    }
}