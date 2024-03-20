package com.maejjang.api.login.service.sns;

public enum SnsType {
    GOOGLE,
    NAVER;

    public static SnsType of(String type) {
        String value = type.toUpperCase();
        for (SnsType snsType: SnsType.values()) {
            if (snsType.name().equalsIgnoreCase(value)) {
                return snsType;
            }
        }
        return null;
    }
}
