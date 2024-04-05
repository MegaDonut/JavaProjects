package org.itmo.services;

import org.itmo.dao.MasterDao;
import org.itmo.pojo.Master;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MasterServiceTest {
    MasterDao masterDao = new MasterDao();
    MasterService masterService = new MasterService(masterDao);


    @Test
    void create() {
        Master master = masterService.create(new Date(1), "bob");
        assertNotNull(masterService.show(master.getId()));
        masterService.delete(master.getId());
    }
}