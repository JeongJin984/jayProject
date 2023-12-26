package com.jay.memberserver.business.service;

import com.auth0.jwt.JWT;
import com.jay.memberserver.common.dto.MemberInfo;
import com.jay.memberserver.common.util.JaySecurityUtil;
import com.jay.memberserver.domain.entity.Member;
import com.jay.memberserver.domain.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JayTokenService {
    @Value("${security.rsa.gen}")
    private boolean gen;

    private final JaySecurityUtil securityUtil;
    private final MemberRepository jayMemberRepository;

    public String createToken(String loginId) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException {
        Optional<Member> member = jayMemberRepository.findByLoginId(loginId);

        if(member.isPresent()) {
            MemberInfo info = member.get().getMemberInfo();
            return JWT.create()
                    .withIssuer("jayAuth0")
                    .withClaim("memberInfo", info.toMap())
                    .sign(securityUtil.jwtSignAlgorithm(gen));
        } else {
            throw new NoSuchElementException("No Such Member");
        }
    }
}
