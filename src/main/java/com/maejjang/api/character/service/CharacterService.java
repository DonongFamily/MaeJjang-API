package com.maejjang.api.character.service;

import com.maejjang.api.character.dto.CharacterBasicDto;
import com.maejjang.api.character.dto.CharacterDto;
import com.maejjang.api.character.feign.CharacterFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CharacterService implements CharacterFeign {

    @Override
    public CharacterDto getCharacterOcid(String apiKey, String characterName) {
        return null;
    }

    @Override
    public CharacterBasicDto getCharacterBasic(String apiKey, String ocid, String date) {
        return null;
    }
}
