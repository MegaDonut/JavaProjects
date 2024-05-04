package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;
import org.itmo.dataaccesslayer2.pojo.Master;

@Getter
@Builder
public class CreateUserRequest {
    private final String login;
    private final String password;
    private final String role;
    private final Master master;
}
