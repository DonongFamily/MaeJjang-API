package com.maejjang.api.common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseEntity {

    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
