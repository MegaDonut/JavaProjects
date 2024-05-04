package org.itmo.servicelayer1.services.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@EqualsAndHashCode
public class CreateShowMasterResponse {
    private final Integer id;
    private final String name;
    private final Date birthday;
}
