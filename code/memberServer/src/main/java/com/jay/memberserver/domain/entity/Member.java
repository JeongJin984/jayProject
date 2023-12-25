package com.jay.memberserver.domain.entity;

import jakarta.persistence.*;

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private MemberType memberType;

    private String username;


}
