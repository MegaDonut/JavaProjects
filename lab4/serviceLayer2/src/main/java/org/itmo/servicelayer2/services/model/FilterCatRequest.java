package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;
import org.itmo.dataaccesslayer2.enums.CatBreed;
import org.itmo.dataaccesslayer2.enums.CatColors;

@Getter
@Builder
public class FilterCatRequest {
    private final CatColors color;
    private final CatBreed breed;
}
