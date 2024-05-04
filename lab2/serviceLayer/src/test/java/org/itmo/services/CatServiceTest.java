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
import static org.mockito.Mockito.*;

class CatServiceTest {

    @Test
    void create() {
        CatDao mockedCatDao = mock(CatDao.class);
        MasterDao mocedMasterDao = mock(MasterDao.class);
        CatService catService = new CatService(mockedCatDao, mocedMasterDao);

        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catN = new Cat();
        catN.setMaster(master);
        catN.setColors(CatColors.Black);
        catN.setBreed(CatBreed.Britain);
        catN.setName("tiger");
        catN.setBirthday(new Date(1));

        Cat catId = new Cat();
        catId.setMaster(master);
        catId.setColors(CatColors.Black);
        catId.setBreed(CatBreed.Britain);
        catId.setName("tiger");
        catId.setBirthday(new Date(1));
        catId.setId(1);

        when(mockedCatDao.save(catN)).thenReturn(catId);

        Cat cat = catService.create("tiger", new Date(1), CatBreed.Britain, CatColors.Black, 1);
        verify(mockedCatDao).save(catN);
        assertEquals(cat, catId);
    }

    @Test
    void setFriends() {
        CatDao mockedCatDao = mock(CatDao.class);
        MasterDao mocedMasterDao = mock(MasterDao.class);
        CatService catService = new CatService(mockedCatDao, mocedMasterDao);


        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catF = spy(new Cat());
        catF.setId(1);
        catF.setMaster(master);
        catF.setColors(CatColors.Black);
        catF.setBreed(CatBreed.Britain);
        catF.setName("tiger");
        catF.setBirthday(new Date(1));

        Cat catS = spy(new Cat());
        catS.setId(2);
        catS.setMaster(master);
        catS.setColors(CatColors.Black);
        catS.setBreed(CatBreed.Britain);
        catS.setName("tiger");
        catS.setBirthday(new Date(1));

        when(mockedCatDao.findById(1)).thenReturn(catF);
        when(mockedCatDao.findById(2)).thenReturn(catS);

        catService.setFriends(catF.getId(), catS.getId());
        verify(catF).getCats();
        verify(catS).getCats();

        assertNotEquals(catF.getCats().size(), 0);
        assertNotEquals(catS.getCats().size(), 0);
    }

    @Test
    void deleteFriendship() {
        CatDao mockedCatDao = mock(CatDao.class);
        MasterDao mocedMasterDao = mock(MasterDao.class);
        CatService catService = new CatService(mockedCatDao, mocedMasterDao);


        Master master = new Master();
        master.setId(1);
        master.setName("bob");
        master.setBirthday(new Date(1));

        Cat catF = spy(new Cat());
        catF.setId(1);
        catF.setMaster(master);
        catF.setColors(CatColors.Black);
        catF.setBreed(CatBreed.Britain);
        catF.setName("tiger");
        catF.setBirthday(new Date(1));

        Cat catS = spy(new Cat());
        catS.setId(2);
        catS.setMaster(master);
        catS.setColors(CatColors.Black);
        catS.setBreed(CatBreed.Britain);
        catS.setName("tiger");
        catS.setBirthday(new Date(1));
        catS.getCats().add(catF);

        catF.getCats().add(catS);

        when(mockedCatDao.findById(1)).thenReturn(catF);
        when(mockedCatDao.findById(2)).thenReturn(catS);

        catService.deleteFriendship(1, 2);

        assertNotEquals(catF.getCats().size(), 1);
        assertNotEquals(catS.getCats().size(), 1);
    }
}