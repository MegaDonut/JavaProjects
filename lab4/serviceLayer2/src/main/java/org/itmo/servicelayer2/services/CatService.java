package org.itmo.servicelayer2.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer2.pojo.Cat;
import org.itmo.dataaccesslayer2.pojo.User;
import org.itmo.dataaccesslayer2.pojo.UserDetail;
import org.itmo.dataaccesslayer2.repositories.CatRepository;
import org.itmo.dataaccesslayer2.repositories.MasterRepository;
import org.itmo.servicelayer2.services.model.ChangeMasterRequest;
import org.itmo.servicelayer2.services.model.CreateCatRequest;
import org.itmo.servicelayer2.services.model.FilterCatRequest;
import org.itmo.servicelayer2.services.model.SetUnsetFriendshipRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Cat> findCats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        User user = userDetail.getUser();

        List<Cat> result;

        if (user.getRole().equals("ADMIN")) {
            result = catRepository.findAll();
        } else {
            result = catRepository.findCatsByMaster(user.getMaster());
        }

        return result;
    }

    public List<Cat> filterCats(FilterCatRequest request) {
        List<Cat> result = findCats();

        if (request.getBreed() != null) {
            result = result.stream().filter(cat -> request.getBreed().equals(cat.getBreed())).toList();
        }

        if (request.getColor() != null) {
            result = result.stream().filter(cat -> request.getColor().equals(cat.getColors())).toList();
        }

        return result;
    }

    public void delete(Integer catId) {
        catRepository.deleteById(catId);
    }
}
