package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CreateMasterRequest {
    private final Date birthday;
    private final String name;

    private final String login;
    private final String password;
    private final String role;
}
