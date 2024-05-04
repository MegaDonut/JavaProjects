package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetUnsetFriendshipRequest {
    private final Integer catIdFirst;
    private final Integer catIdSecond;
}
