package org.itmo.application1.model;

import lombok.Builder;
import lombok.Getter;
import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;


import java.util.Date;

@Getter
@Builder
public class CreateCatRequest {
    private final String name;
    private final Date birthday;
    private final Integer masterId;
    private final CatBreed breed;
    private final CatColors color;
}
