package org.itmo.application1.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeMasterRequest {
    private final Integer catId;
    private final Integer newMasterId;
}
