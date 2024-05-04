package org.itmo.servicelayer1.services.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeMasterRequest {
    private final Integer catId;
    private final Integer newMasterId;
}
