package com.jay.memberserver.common.dto;

import com.jay.memberserver.domain.entity.Member;
import com.jay.memberserver.domain.entity.MemberType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SignupInfo {
    private String username;
    private String loginId;

    public Member buildMember() {
        return new Member(UUID.randomUUID().toString(), MemberType.Child, username, loginId);
    }
}
