package com.ruzhkov.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterServiceTest {

    private CharacterService characterService;

    private final String WARNING = """
            Введенная строка должна быть непустой и состоять только из латинских символов(a-zA-Z) и цифр без пробелов.
            Попрубуйте еще раз.
            """;

    @BeforeEach
    void init(){
        characterService = new CharacterService();
    }


    @Test
    void ifInvalidString(){
        String INVALID_STRING = "AAAka   ##$%fJKLHJlkjfa&^laskjd";
        var result = characterService.calculateFrequencyOfCharacters(INVALID_STRING);

        assertTrue(result instanceof String, "Incorrect return type");
        assertEquals(WARNING, result);
    }

    @Test
    void ifEmptyString(){
        var emptyString = "";
        var result = characterService.calculateFrequencyOfCharacters(emptyString);
        assertTrue(result instanceof String, "Incorrect return type");
        assertEquals(WARNING, result);
    }

    @Test
    void ifValidString(){
        String VALID_STRING = "aaaaabccdddddddddddddddd12344444";
        var result = characterService.calculateFrequencyOfCharacters(VALID_STRING);

        if(result instanceof LinkedHashMap resultMap){
            LinkedHashMap<Integer, List<Character>> resMap = resultMap;
            final String ERROR = "Incorrect size of listCharacters";

            assertFalse(resMap.isEmpty(), "Map should be not empty");
            assertEquals(2, resMap.get(5).size(), ERROR);
            assertEquals(4, resMap.get(1).size(), ERROR);
            assertEquals(1, resMap.get(16).size(), ERROR);
            assertEquals(1, resMap.get(2).size(), ERROR);

        }else{
            fail("Incorrect return type");
        }
    }
}
