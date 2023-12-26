package com.jay.memberserver.domain.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MemberType {
    FATHER("아버지", "M001"),
    MOTHER("어머니", "M002"),
    SON("아들", "M003"),
    DAUGHTER("딸", "M004"),
    NONE("없음", "M000");

    private String desc;
    private String code;

    MemberType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public static MemberType fromCode(String code) {
        return Arrays.stream(MemberType.values())
                .filter(v -> v.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No Item is found by this code(%s)", code)));
    }
}
