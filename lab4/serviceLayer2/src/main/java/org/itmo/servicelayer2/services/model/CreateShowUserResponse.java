package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateShowUserResponse {
    private final String login;
    private final String password;
}
