package org.itmo.servicelayer1.services.mapper;

import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.servicelayer1.services.model.CreateShowCatResponse;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.servicelayer1.services.model.CreateShowMasterResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public CreateShowCatResponse toCreateShowCatResponse(Cat cat) {
        return CreateShowCatResponse.builder()
                .id(cat.getId())
                .color(cat.getColors())
                .breed(cat.getBreed())
                .name(cat.getName())
                .masterId(cat.getMaster().getId())
                .birthday(cat.getBirthday())
                .build();
    }

    public CreateShowMasterResponse toCreateShowMasterResponse(Master master) {
        return CreateShowMasterResponse
                .builder()
                .id(master.getId())
                .name(master.getName())
                .birthday(master.getBirthday())
                .build();
    }


}
