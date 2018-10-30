package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
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
    public void shouldFetchTrelloBoard() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("id", "name", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("id2", "name2", new ArrayList<>()));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoard();

        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void createdTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        BadgesDto badgesDto = new BadgesDto(3, new TrelloDto());
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id", "name", "shortUrl", badgesDto);

        when(trelloClient.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto result = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("id", result.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("shortUrl", createdTrelloCardDto.getShortUrl());
        assertEquals(badgesDto, createdTrelloCardDto.getBadges());
    }

}
