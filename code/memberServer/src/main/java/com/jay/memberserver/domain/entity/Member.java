package com.jay.memberserver.domain.entity;

import com.jay.memberserver.common.dto.MemberInfo;
import com.jay.memberserver.common.dto.SignupInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "member")
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;

    private MemberType memberType;

    private String username;

    private String loginId;

    public MemberInfo getMemberInfo() {
        return MemberInfo.builder()
                .memberId(memberId)
                .memberType(memberType)
                .username(username)
                .loginId(loginId)
                .build();
    }
}
