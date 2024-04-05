package org.itmo.services;

import org.itmo.dao.CatDao;
import org.itmo.dao.MasterDao;
import org.itmo.enums.CatBreed;
import org.itmo.enums.CatColors;
import org.itmo.pojo.Cat;
import org.itmo.pojo.Master;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CatServiceTest {
    CatDao catDao = new CatDao();
    MasterDao masterDao = new MasterDao();
    CatService catService = new CatService(catDao, masterDao);
    MasterService masterService = new MasterService(masterDao);

    @Test
    void create() {
        Master master = masterService.create(new Date(1), "bob");

        Cat cat = catService.create("tiger", new Date(1), CatBreed.Britain, CatColors.Black, master.getId());
        assertNotNull(catService.show(cat.getId()));

        catService.delete(cat.getId());
        masterService.delete(master.getId());
    }

    @Test
    void setFriends() {
        Master master = masterService.create(new Date(1), "bob");
        Cat catF = catService.create("tiger1", new Date(1), CatBreed.Britain, CatColors.Black, master.getId());
        Cat catS = catService.create("tiger2", new Date(1), CatBreed.Britain, CatColors.Black, master.getId());

        assertEquals(catF.getCats().size(), 0);
        assertEquals(catS.getCats().size(), 0);

        catService.setFriends(catF.getId(), catS.getId());

        assertEquals(catService.show(catF.getId()).getCats().size(), 1);
        assertEquals(catService.show(catS.getId()).getCats().size(), 1);

        catService.delete(catF.getId());
        catService.delete(catS.getId());
        masterService.delete(master.getId());
    }

    @Test
    void deleteFriendship() {
        Master master = masterService.create(new Date(1), "bob");
        Cat catF = catService.create("tiger1", new Date(1), CatBreed.Britain, CatColors.Black, master.getId());
        Cat catS = catService.create("tiger2", new Date(1), CatBreed.Britain, CatColors.Black, master.getId());

        catService.setFriends(catF.getId(), catS.getId());
        assertEquals(catService.show(catF.getId()).getCats().size(), 1);
        assertEquals(catService.show(catS.getId()).getCats().size(), 1);

        catService.deleteFriendship(catF.getId(), catS.getId());

        assertEquals(catService.show(catF.getId()).getCats().size(), 0);
        assertEquals(catService.show(catS.getId()).getCats().size(), 0);

        catService.delete(catF.getId());
        catService.delete(catS.getId());
        masterService.delete(master.getId());
    }

    @Test
    void changeMaster() {
        Master masterF = masterService.create(new Date(1), "bob1");
        Master masterS = masterService.create(new Date(1), "bob2");

        Cat cat = catService.create("tiger", new Date(1), CatBreed.Britain, CatColors.Black, masterF.getId());

        assertEquals(cat.getMaster().getId(), masterF.getId());

        catService.changeMaster(cat.getId(), masterS.getId());

        assertEquals(catService.show(cat.getId()).getMaster().getId(), masterS.getId());

        masterService.delete(masterF.getId());
        masterService.delete(masterS.getId());
        catService.delete(cat.getId());
    }

}