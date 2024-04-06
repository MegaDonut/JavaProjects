package org.itmo.services;

import org.itmo.dao.MasterDao;
import org.itmo.pojo.Master;
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
        MasterDao mockedMasterDao = mock(MasterDao.class);
        when(mockedMasterDao.save(masterN)).thenReturn(masterId);
        MasterService masterService = new MasterService(mockedMasterDao);

        Master master = masterService.create(new Date(1), "bob");
        verify(mockedMasterDao).save(masterN);
        assertEquals(masterId, master);
    }
}