package com.jay.memberserver.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ClientResponse {
    private ClientStatus status;
    private String message;
    private Object data;

    @Getter
    public enum ClientStatus {
        SUCCEED("0000"), NO_SUCH_ELEMENT("0001"), SERVER_ERROR("0002");

        private final String code;

        ClientStatus(String s) {
            this.code = s;
        }
    }
}
