package com.maejjang.api.character.feign;

import com.maejjang.api.character.dto.CharacterBasicDto;
import com.maejjang.api.character.dto.CharacterDto;
import com.maejjang.api.config.feign.FeignHeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CharacterFeign", url = "${maple.api.uri.character}", configuration = {FeignHeaderConfiguration.class})
public interface CharacterFeign {

    /**
     * 캐릭터 식별자(ocid) 조회
     */
    @GetMapping("/id")
    CharacterDto getCharacterOcid(@RequestParam("character_name") String characterName);

    /**
     * 캐릭터 기본 정보 조회
     */
    @GetMapping("/character/basic")
    CharacterBasicDto getCharacterBasic(@RequestParam("ocid") String ocid,
                                        @RequestParam("date") String date);
}
