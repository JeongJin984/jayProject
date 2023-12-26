package com.jay.memberserver.domain.entity;

import com.jay.memberserver.common.dto.MemberInfo;
import com.jay.memberserver.common.dto.SignupInfo;
import com.jay.memberserver.domain.converter.MemberTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.convert.WritingConverter;

@Entity(name = "jayMember")
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String memberId;
    @Convert(converter = MemberTypeConverter.class)
    private MemberType memberType;
    private String username;
    private String loginId;

    public MemberInfo getMemberInfo() {
        return MemberInfo.builder()
                .memberId(memberId)
                .memberType(memberType.getCode())
                .username(username)
                .loginId(loginId)
                .build();
    }
}
