package com.maejjang.api.character.dto;

import lombok.Getter;

@Getter
public class CharacterBasicDto {

    /** 조회 기준일 */
    private String date;

    /** 캐릭터 명 */
    private String characterName;

    /** 월드 명 */
    private String worldName;

    /** 캐릭터 성별 */
    private String characterGender;

    /** 캐릭터 직업 */
    private String characterClass;

    /** 캐릭터 전직 차수 */
    private String characterClassLevel;

    /** 캐릭터 레벨 */
    private int characterLevel;

    /** 현재 레벨에서 보유한 경험치 */
    private int characterExp;

    /** 현재 레벨에서 경험치 퍼센트 */
    private String characterExpRate;

    /** 캐릭터 소속 길드 명 */
    private String characterGuildName;

    /** 캐릭터 외형 이미지 */
    private String characterImage;
}
