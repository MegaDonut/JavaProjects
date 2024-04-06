package org.itmo.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dao.CatDao;
import org.itmo.dao.MasterDao;
import org.itmo.enums.CatBreed;
import org.itmo.enums.CatColors;
import org.itmo.pojo.Cat;

import java.util.Date;

@RequiredArgsConstructor
public class CatService {
    final CatDao catDao;
    final MasterDao masterDao;
    public Cat create(String name, Date birthday, CatBreed breed, CatColors colors, Integer masterId) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setBirthday(birthday);
        cat.setBreed(breed);
        cat.setColors(colors);
        cat.setMaster(masterDao.findById(masterId));
        cat = catDao.save(cat);
        return cat;
    }
    public Cat show(Integer catId) {
        return catDao.findById(catId);
    }

    public void setFriends(Integer catIdFirst, Integer catIdSecond) {
        Cat catFirst = catDao.findById(catIdFirst);
        Cat catSecond = catDao.findById(catIdSecond);
        catFirst.getCats().add(catSecond);
        catSecond.getCats().add(catFirst);
        catDao.save(catFirst);
    }

    public void deleteFriendship(Integer catIdFirst, Integer catIdSecond) {
        Cat catFirst = catDao.findById(catIdFirst);
        Cat catSecond = catDao.findById(catIdSecond);
        catFirst.getCats().remove(catSecond);
        catSecond.getCats().remove(catFirst);
        catDao.save(catFirst);
    }

    public void changeMaster(Integer catId, Integer newMasterId) {
        Cat cat = catDao.findById(catId);
        cat.setMaster(masterDao.findById(newMasterId));
        catDao.save(cat);
    }

    public void delete(Integer catId) {
        catDao.delete(catDao.findById(catId));
    }
}
