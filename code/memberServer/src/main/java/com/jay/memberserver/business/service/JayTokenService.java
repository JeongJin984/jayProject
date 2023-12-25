package com.jay.memberserver.business.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jay.memberserver.common.util.JaySecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class JayTokenService {
    @Value("${security.rsa.gen}")
    private boolean gen;

    private final JaySecurityUtil securityUtil;

    public String createToken() throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException {
        return JWT.create()
                .withIssuer("jayAuth0")
                .sign(securityUtil.jwtSignAlgorithm(gen));
    }
}
