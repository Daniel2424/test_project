package com.ruzhkov.test.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CharacterService {
    private static final String WARNING = """
            Введенная строка должна быть непустой и состоять только из латинских символов(a-zA-Z) и цифр без пробелов.
            Попрубуйте еще раз.
            """;

    public Object calculateFrequencyOfCharacters(String str) { // O(3n) -> O(n)
        if (!str.matches("^[a-zA-Z\\d]+$")) {
            return WARNING;
        }

        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, List<Character>> reverseMap = new HashMap<>();
        var max = 0;

        for (int i = 0; i < str.length(); i++) { //O(n)
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            max = Math.max(max, map.get(ch));
        }
        for (Map.Entry<Character, Integer> entry: map.entrySet()) { //O(n)
            var ch = entry.getKey();
            var count = entry.getValue();

            if(reverseMap.containsKey(count)){
                reverseMap.get(count).add(ch);
            }else{
                reverseMap.put(count, new ArrayList<>(List.of(ch)));
            }

        }
        Map<Integer, List<Character>> resultMap = new LinkedHashMap<>();

        for (int count = max; count > 0; count--) { //O(n)
            if(reverseMap.containsKey(count)){
                var listCharacters = reverseMap.get(count);
                resultMap.put(count, listCharacters);
            }
        }

        return resultMap;
    }


}
