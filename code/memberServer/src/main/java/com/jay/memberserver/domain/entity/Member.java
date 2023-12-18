package com.jay.memberserver.domain.entity;

import jakarta.persistence.*;

@Table(name = "member")
@Entity(name = "memberEntity")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private MemberType memberType;

    private String username;


}
