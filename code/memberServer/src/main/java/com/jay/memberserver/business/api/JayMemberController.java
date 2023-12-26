package com.jay.memberserver.business.api;

import com.jay.memberserver.business.service.JayTokenService;
import com.jay.memberserver.business.service.MemberService;
import com.jay.memberserver.common.dto.ClientResponse;
import com.jay.memberserver.common.dto.SignupInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class JayMemberController {
    private final JayTokenService jayTokenService;
    private final MemberService memberService;

    @GetMapping("/login")
    public ResponseEntity<ClientResponse> login(@RequestParam String loginId) {
        ClientResponse body;
        try {
            String token = jayTokenService.createToken(loginId);

            return ResponseEntity.ok(
                    ClientResponse.builder()
                            .status(ClientResponse.ClientStatus.SUCCEED)
                            .message("token created")
                            .data(token)
                            .build()
            );
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                 | IOException | InvalidKeySpecException e) {
            log.error("Exception: ", e);

            return ResponseEntity.internalServerError().body(
                    ClientResponse.builder()
                            .status(ClientResponse.ClientStatus.SERVER_ERROR)
                            .message("Server Error")
                            .build()
            );
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ClientResponse> signup(
            @RequestBody SignupInfo signupInfo
    ) {
        try {
            memberService.signup(signupInfo);
            return ResponseEntity.ok(
                    ClientResponse.builder()
                            .status(ClientResponse.ClientStatus.SUCCEED)
                            .message("Signup Succeed")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    ClientResponse.builder()
                            .status(ClientResponse.ClientStatus.SERVER_ERROR)
                            .message("Server Error")
                            .build()
            );
        }

    }
}
