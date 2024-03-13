package com.maejjang.api.character.controller;

import com.maejjang.api.character.dto.CharacterDto;
import com.maejjang.api.character.service.CharacterService;
import com.maejjang.api.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/test")
    public ApiResponse<CharacterDto> test(@RequestParam String name) {
        CharacterDto characterOcid = characterService.getCharacterOcid(name);

        return ApiResponse.ok(characterOcid);
    }
}
