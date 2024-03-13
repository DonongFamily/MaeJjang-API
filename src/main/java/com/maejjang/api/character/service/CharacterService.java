package com.maejjang.api.character.service;

import com.maejjang.api.character.dto.CharacterDto;
import com.maejjang.api.character.feign.CharacterFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterFeign characterFeign;

    public CharacterDto getCharacterOcid(String name) {
        return characterFeign.getCharacterOcid(name);
    }
}
