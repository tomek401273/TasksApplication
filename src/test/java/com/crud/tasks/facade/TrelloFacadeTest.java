package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.domain.trello.card.AttachmentsByType;
import com.crud.tasks.domain.trello.card.Badges;
import com.crud.tasks.domain.trello.card.CreatedTrelloCardDto;
import com.crud.tasks.domain.trello.card.Trello;
import com.crud.tasks.domain.trello.validator.TrelloValidator;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shoutldCreateCard() {
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "desc", "pos", "listId");
        TrelloCard trelloCard = new TrelloCard("test", "desc", "pos", "listId");
        Trello trello = new Trello(1, 2);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(3, attachmentsByType);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "test", "shortUrl", badges);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        // When
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);
        // Then
        Assert.assertEquals(createdCard.getId(), "1");
        Assert.assertEquals(createdCard.getBadges().getVotes(),3);
        Assert.assertEquals(createdCard.getName(), "test");
        Assert.assertEquals(createdCard.getShortUrl(), "shortUrl");
    }

    @Test
    public void shoutldCreateCard2() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(null, null, null, null);
        TrelloCard trelloCard = new TrelloCard(null, null, null, null);
        Trello trello = new Trello(0, 0);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(0, attachmentsByType);

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(null, null, null, badges);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        // When
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);
        // Then
        Assert.assertEquals(createdCard.getId(), null);
        Assert.assertEquals(createdCard.getBadges().getVotes(),0);
        Assert.assertEquals(createdCard.getName(), null);
        Assert.assertEquals(createdCard.getShortUrl(), null);
    }

    @Test
    public void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloList));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards))
                .thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(trelloBoardDto -> {
            Assert.assertEquals("1", trelloBoardDto.getId());
            Assert.assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assert.assertEquals("1", trelloListDto.getId());
                Assert.assertEquals("my_list", trelloListDto.getName());
                Assert.assertEquals(false, trelloListDto.isClosed());
            });

        });

    }

    @Test
    public void shouldFetchEmptyList() {
        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloList));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assert.assertNotNull(trelloBoardDtos);
        Assert.assertEquals(0, trelloBoardDtos.size());
    }


}
