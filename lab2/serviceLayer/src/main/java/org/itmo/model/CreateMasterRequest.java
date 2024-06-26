package org.itmo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CreateMasterRequest {
    private final Date birthday;
    private final String name;
}
