
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
    }
//
//    @Test
//    public void shouldCreateCard() throws URISyntaxException {
//        //Given
//        TrelloCardDto trelloCardDto = new TrelloCardDto(
//              "Task task",
//              "Test Description",
//              "top",
//              "test_id"
//      );
//
//       // URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
//      //  URI uri = new URI("http://test.comnull?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
//
////                URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + trelloConfig.getTrelloUsername())
////                .queryParam("key", trelloConfig.getTrelloAppKey())
////                .queryParam("token", trelloConfig.getTrelloToken())
////                .queryParam("fields", "name,id")
////                .queryParam("lists", "all").build().encode().toUri();
////
////        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
////                "1",
////                "Test task",
////                "http://test.com"
////        );
//
//        System.out.println("trelloCardDto: "+trelloCardDto.toString());
//        System.out.println("return TrelloClient: "+trelloClient.createNewCard(trelloCardDto));
//        //When
//        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
//        System.out.println("newCard: "+newCard.toString());
//
//        //Then
//        assertEquals("1", "1");
//        assertEquals("Task task", newCard.getName());
//        assertEquals("http://test.com",newCard.getShortUrl());
//
//
//    }


    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        // Given



        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

     //   URI uri = new URI("http://test.com/members/kodillauser/boards?key=test&token=test&fields=name,id&lists=all");

//        URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + trelloConfig.getTrelloUsername())
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all").build().encode().toUri();

           URI uri = new URI("http://test.comnull?key=test&token=test&fields=name,id&lists=all");


        System.out.println("URI URI: "+uri.toString());
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //Then
        List<TrelloBoardDto> fetchTrelloBoards = trelloClient.getTrelloBoards();

        assertEquals(1, fetchTrelloBoards.size());
        assertEquals("test_id", fetchTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchTrelloBoards.get(0).getLists());
    }


}