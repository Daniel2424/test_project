package com.ruzhkov.test.controllers;

import com.ruzhkov.test.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @GetMapping("/api")
    public Object frequencyOfCharacters(@RequestParam("str") String str){
        return characterService.calculateFrequencyOfCharacters(str);

    }
}
