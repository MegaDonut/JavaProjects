package org.itmo.servicelayer1.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.dataaccesslayer1.repositories.CatRepository;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.itmo.servicelayer1.services.model.ChangeMasterRequest;
import org.itmo.servicelayer1.services.model.CreateCatRequest;
import org.itmo.servicelayer1.services.model.SetUnsetFriendshipRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    private final MasterRepository masterRepository;
    public Cat create(CreateCatRequest request) {
        Cat cat = new Cat();
        cat.setName(request.getName());
        cat.setBirthday(request.getBirthday());
        cat.setBreed(request.getBreed());
        cat.setColors(request.getColor());
        cat.setMaster(masterRepository.findMasterById(request.getMasterId()));
        cat = catRepository.save(cat);
        return cat;
    }
    public Cat show(Integer catId) {
        return catRepository.findCatById(catId);
    }

    public void setFriends(SetUnsetFriendshipRequest request) {
        Cat catFirst = catRepository.findCatById(request.getCatIdFirst());
        Cat catSecond = catRepository.findCatById(request.getCatIdSecond());
        catFirst.getCats().add(catSecond);
        catSecond.getCats().add(catFirst);
        catRepository.save(catFirst);
    }

    public void deleteFriendship(SetUnsetFriendshipRequest request) {
        Cat catFirst = catRepository.findCatById(request.getCatIdFirst());
        Cat catSecond = catRepository.findCatById(request.getCatIdSecond());
        catFirst.getCats().remove(catSecond);
        catSecond.getCats().remove(catFirst);
        catRepository.save(catFirst);
    }

    public void changeMaster(ChangeMasterRequest request) {
        Cat cat = catRepository.findCatById(request.getCatId());
        cat.setMaster(masterRepository.findMasterById(request.getNewMasterId()));
        catRepository.save(cat);
    }

    public void delete(Integer catId) {
        catRepository.deleteById(catId);
    }
}
