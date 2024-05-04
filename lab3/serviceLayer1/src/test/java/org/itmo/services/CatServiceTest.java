package org.itmo.services;


import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.dataaccesslayer1.repositories.CatRepository;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.itmo.servicelayer1.services.CatService;
import org.itmo.servicelayer1.services.model.CreateCatRequest;
import org.itmo.servicelayer1.services.model.SetUnsetFriendshipRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceTest {

    @Test
    void create() {
        CatRepository mockedCatRepo = mock(CatRepository.class);
        MasterRepository mockedMasterRepo = mock(MasterRepository.class);
        CatService catService = new CatService(mockedCatRepo, mockedMasterRepo);

        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catN = new Cat();
        catN.setMaster(master);
        catN.setColors(CatColors.BLACK);
        catN.setBreed(CatBreed.BRITAIN);
        catN.setName("tiger");
        catN.setBirthday(new Date(1));

        Cat catId = new Cat();
        catId.setMaster(master);
        catId.setColors(CatColors.BLACK);
        catId.setBreed(CatBreed.BRITAIN);
        catId.setName("tiger");
        catId.setBirthday(new Date(1));
        catId.setId(1);

        when(mockedCatRepo.save(catN)).thenReturn(catId);
        CreateCatRequest request = CreateCatRequest.builder()
                .name("tiger")
                .birthday(new Date(1))
                .breed(CatBreed.BRITAIN)
                .color(CatColors.BLACK)
                .masterId(1)
                .build();

        Cat cat = catService.create(request);
        verify(mockedCatRepo).save(catN);
        assertEquals(cat, catId);
    }

    @Test
    void setFriends() {
        CatRepository mockedCatRepo = mock(CatRepository.class);
        MasterRepository mockedMasterRepo = mock(MasterRepository.class);
        CatService catService = new CatService(mockedCatRepo, mockedMasterRepo);


        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catF = spy(new Cat());
        catF.setId(1);
        catF.setMaster(master);
        catF.setColors(CatColors.BLACK);
        catF.setBreed(CatBreed.BRITAIN);
        catF.setName("tiger");
        catF.setBirthday(new Date(1));

        Cat catS = spy(new Cat());
        catS.setId(2);
        catS.setMaster(master);
        catS.setColors(CatColors.BLACK);
        catS.setBreed(CatBreed.BRITAIN);
        catS.setName("tiger");
        catS.setBirthday(new Date(1));

        when(mockedCatRepo.findCatById(1)).thenReturn(catF);
        when(mockedCatRepo.findCatById(2)).thenReturn(catS);

        SetUnsetFriendshipRequest request = SetUnsetFriendshipRequest.builder().catIdFirst(1).catIdSecond(2).build();
        catService.setFriends(request);
        verify(catF).getCats();
        verify(catS).getCats();

        assertNotEquals(catF.getCats().size(), 0);
        assertNotEquals(catS.getCats().size(), 0);
    }

    @Test
    void deleteFriendship() {
        CatRepository mockedCatRepo = mock(CatRepository.class);
        MasterRepository mockedMasterRepo = mock(MasterRepository.class);
        CatService catService = new CatService(mockedCatRepo, mockedMasterRepo);


        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catF = spy(new Cat());
        catF.setId(1);
        catF.setMaster(master);
        catF.setColors(CatColors.BLACK);
        catF.setBreed(CatBreed.BRITAIN);
        catF.setName("tiger");
        catF.setBirthday(new Date(1));

        Cat catS = spy(new Cat());
        catS.setId(2);
        catS.setMaster(master);
        catS.setColors(CatColors.BLACK);
        catS.setBreed(CatBreed.BRITAIN);
        catS.setName("tiger");
        catS.setBirthday(new Date(1));
        catS.getCats().add(catF);

        catF.getCats().add(catS);

        when(mockedCatRepo.findCatById(1)).thenReturn(catF);
        when(mockedCatRepo.findCatById(2)).thenReturn(catS);
        SetUnsetFriendshipRequest request = SetUnsetFriendshipRequest.builder().catIdFirst(1).catIdSecond(2).build();
        catService.deleteFriendship(request);

        assertNotEquals(catF.getCats().size(), 1);
        assertNotEquals(catS.getCats().size(), 1);
    }
}