package org.itmo.servicelayer1.services.mapper;

import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.servicelayer1.services.model.CreateShowCatResponse;
import org.itmo.servicelayer1.services.model.CreateShowMasterResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    private final Mapper mapper = new Mapper();

    @Test
    void toCreateShowCatResponse() {
        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catN = new Cat();
        catN.setId(1);
        catN.setMaster(master);
        catN.setColors(CatColors.BLACK);
        catN.setBreed(CatBreed.BRITAIN);
        catN.setName("tiger");
        catN.setBirthday(new Date(1));

        CreateShowCatResponse request = CreateShowCatResponse.builder()
                .id(1)
                .color(CatColors.BLACK)
                .breed(CatBreed.BRITAIN)
                .name("tiger")
                .masterId(1)
                .birthday(new Date(1))
                .build();

        assertEquals(request, mapper.toCreateShowCatResponse(catN));
    }

    @Test
    void toCreateShowMasterResponse() {
        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        CreateShowMasterResponse request = CreateShowMasterResponse.builder().name("bob").id(1).birthday(new Date(1)).build();

        assertEquals(request, mapper.toCreateShowMasterResponse(master));
    }
}