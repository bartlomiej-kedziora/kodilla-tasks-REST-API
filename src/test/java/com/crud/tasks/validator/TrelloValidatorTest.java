package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("id", "name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("id2", "name2", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("id3", "test", new ArrayList<>()));

        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(2, result.size());
    }
}
