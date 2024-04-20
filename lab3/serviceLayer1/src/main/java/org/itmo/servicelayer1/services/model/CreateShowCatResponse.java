package org.itmo.servicelayer1.services.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;

import java.util.Date;

@Getter
@Builder
@EqualsAndHashCode
public class CreateShowCatResponse {
    private final Integer id;
    private final String name;
    private final Date birthday;
    private final Integer masterId;
    private final CatBreed breed;
    private final CatColors color;
}
