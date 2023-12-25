package com.jay.memberserver.common.dto;

import com.jay.memberserver.domain.entity.MemberType;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class MemberInfo {
    private String memberId;
    private String username;
    private String loginId;
    private MemberType memberType;

    public Map<String, String> toMap() {
        return Map.of(
                "memberId", this.memberId,
                "username", this.username,
                "loginId", this.loginId,
                "memberType", this.memberType.name()
        );
    }


}
