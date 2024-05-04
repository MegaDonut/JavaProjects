package org.itmo.servicelayer2.services.model;

import lombok.Builder;
import lombok.Getter;
import org.itmo.dataaccesslayer2.enums.CatBreed;
import org.itmo.dataaccesslayer2.enums.CatColors;

import java.util.Date;

@Getter
@Builder
public class CreateShowCatResponse {
    private final Integer id;
    private final String name;
    private final Date birthday;
    private final Integer masterId;
    private final CatBreed breed;
    private final CatColors color;
}
