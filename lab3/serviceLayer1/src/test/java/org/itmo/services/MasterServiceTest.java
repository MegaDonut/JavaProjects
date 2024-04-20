package org.itmo.services;

import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.itmo.servicelayer1.services.MasterService;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MasterServiceTest {

    @Test
    void create() {
        Master masterId = new Master();
        masterId.setName("bob");
        masterId.setBirthday(new Date(1));
        masterId.setId(1);

        Master masterN = new Master();
        masterN.setName("bob");
        masterN.setBirthday(new Date(1));

        MasterRepository mockedMasterRepo = mock(MasterRepository.class);
        when(mockedMasterRepo.save(masterN)).thenReturn(masterId);
        MasterService masterService = new MasterService(mockedMasterRepo);

        Master master = masterService.create(new Date(1), "bob");
        verify(mockedMasterRepo).save(masterN);
        assertEquals(masterId, master);
    }
}