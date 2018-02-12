
package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.trello.card.CreatedTrelloCard;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        //System.out.println(restTemplate.getUriTemplateHandler().toString());
       // when(trelloConfig.getTrelloUsername()).thenReturn("tomek");
        when(trelloConfig.getTrelloUsername()).thenReturn("/members/tomek371240/boards");

    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        //when(trelloConfig.getTrelloUsername()).thenReturn(null);

        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

       URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");


        System.out.println("Uri: "+uri);
        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(uri, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void votam() throws URISyntaxException {
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[0];
//        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        // when(trelloConfig.getTrelloUsername()).thenReturn(null);
      //   when(trelloConfig.getTrelloUsername()).thenReturn("tomek");

      //  URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
        URI uri = new URI("http://test.com/members/tomek371240/boards?key=test&token=test&fields=name,id&lists=all");

       // URI uri = new URI("http://test.comnull?key=test&token=test&fields=name,id&lists=all");

//        URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + trelloConfig.getTrelloUsername())
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all").build().encode().toUri();


        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //Then
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        assertEquals(0, fetchTrelloBoards.size());
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        // Given


        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

      // when(trelloConfig.getTrelloUsername()).thenReturn(null);

       URI uri = new URI("http://test.com/members/tomek371240/boards?key=test&token=test&fields=name,id&lists=all");
        System.out.println("username: "+trelloConfig.getTrelloUsername());

//        URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + trelloConfig.getTrelloUsername())
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all").build().encode().toUri();
//        URI uri = new URI("http://test.com/tomek?                     key=test&token=test&fields=name,id&lists=all");


        System.out.println("URI URI: " + uri.toString());
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //Then
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        assertEquals(1, fetchTrelloBoards.size());
        assertEquals("test_id", fetchTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchTrelloBoards.get(0).getLists());
    }

//    @Test
//    public void shouldReturnEmptyList() throws URISyntaxException {
//        // Given
//        URI uri = new URI("http://test.com/tomek?key=test&token=test&fields=name,id&lists=all");
//
//        when(restTemplate.getForObject(uri, RestTemplate.class)).thenReturn(null);
//
//        // When
//        List<TrelloBoardDto> attemptToGetTrelloBoards = trelloClient.getTrelloBoards();
//
//        // Then
//        assertEquals(0,attemptToGetTrelloBoards.size());
//    }





}