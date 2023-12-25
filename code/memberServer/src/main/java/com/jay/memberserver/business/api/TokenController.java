package com.jay.memberserver.business.api;

import com.jay.memberserver.business.service.JayTokenService;
import com.jay.memberserver.common.dto.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {
    private final JayTokenService jayTokenService;
    @GetMapping("/get")
    public ResponseEntity<ClientResponse> token() {
        ClientResponse body;
        try {
            String token = jayTokenService.createToken();
            body = ClientResponse.builder()
                    .status(ClientResponse.ClientStatus.SUCCEED)
                    .message("token created")
                    .data(token)
                    .build();
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                 | IOException | InvalidKeySpecException e) {
            log.error("Exception: ", e);
            body = ClientResponse.builder()
                    .status(ClientResponse.ClientStatus.SERVER_ERROR)
                    .message("Server Error")
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }
}
