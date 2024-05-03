package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CreateShowMasterResponse {
    private final Integer id;
    private final String name;
    private final Date birthday;
}
