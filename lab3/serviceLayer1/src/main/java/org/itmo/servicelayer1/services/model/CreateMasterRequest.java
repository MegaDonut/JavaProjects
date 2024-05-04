package org.itmo.servicelayer1.services.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CreateMasterRequest {
    private final Date birthday;
    private final String name;
}
