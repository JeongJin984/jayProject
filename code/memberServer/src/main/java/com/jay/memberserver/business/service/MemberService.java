package com.jay.memberserver.business.service;

import com.jay.memberserver.common.dto.SignupInfo;
import com.jay.memberserver.domain.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository jayMemberRepository;

    public void signup(SignupInfo info) {
        jayMemberRepository.save(info.buildMember());
    }
}
