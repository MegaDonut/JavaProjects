package org.itmo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CreateShowMasterResponse {
    private final Integer id;
    private final String name;
    private final Date birthday;
}
