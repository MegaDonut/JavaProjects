package org.itmo.servicelayer1.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer1.enums.CatBreed;
import org.itmo.dataaccesslayer1.enums.CatColors;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.dataaccesslayer1.repositories.CatRepository;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    private final MasterRepository masterRepository;
    public Cat create(String name, Date birthday, CatBreed breed, CatColors colors, Integer masterId) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setBirthday(birthday);
        cat.setBreed(breed);
        cat.setColors(colors);
        cat.setMaster(masterRepository.findMasterById(masterId));
        cat = catRepository.save(cat);
        return cat;
    }
    public Cat show(Integer catId) {
        return catRepository.findCatById(catId);
    }

    public void setFriends(Integer catIdFirst, Integer catIdSecond) {
        Cat catFirst = catRepository.findCatById(catIdFirst);
        Cat catSecond = catRepository.findCatById(catIdSecond);
        catFirst.getCats().add(catSecond);
        catSecond.getCats().add(catFirst);
        catRepository.save(catFirst);
    }

    public void deleteFriendship(Integer catIdFirst, Integer catIdSecond) {
        Cat catFirst = catRepository.findCatById(catIdFirst);
        Cat catSecond = catRepository.findCatById(catIdSecond);
        catFirst.getCats().remove(catSecond);
        catSecond.getCats().remove(catFirst);
        catRepository.save(catFirst);
    }

    public void changeMaster(Integer catId, Integer newMasterId) {
        Cat cat = catRepository.findCatById(catId);
        cat.setMaster(masterRepository.findMasterById(newMasterId));
        catRepository.save(cat);
    }

    public void delete(Integer catId) {
        catRepository.deleteById(catId);
    }
}
