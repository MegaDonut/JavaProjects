package org.itmo.controllers;

import org.itmo.dao.CatDao;
import org.itmo.dao.MasterDao;
import org.itmo.model.ChangeMasterRequest;
import org.itmo.model.CreateCatRequest;
import org.itmo.model.CreateShowCatResponse;
import org.itmo.model.SetUnsetFriendshipRequest;
import org.itmo.pojo.Cat;
import org.itmo.services.CatService;

public class CatController {
    CatDao catDao = new CatDao();
    MasterDao masterDao = new MasterDao();
    CatService catService = new CatService(catDao, masterDao);

    public CreateShowCatResponse createCat(CreateCatRequest request) {
        Cat cat = catService.create(request.getName(),
                request.getBirthday(),
                request.getBreed(),
                request.getColor(),
                request.getMasterId());

        return CreateShowCatResponse.builder()
                .id(cat.getId())
                .color(cat.getColors())
                .breed(cat.getBreed())
                .name(cat.getName())
                .masterId(cat.getMaster().getId())
                .birthday(cat.getBirthday())
                .build();
    }

    public CreateShowCatResponse showCat(Integer id) {
        Cat cat = catService.show(id);

        return CreateShowCatResponse.builder()
                .id(cat.getId())
                .color(cat.getColors())
                .breed(cat.getBreed())
                .name(cat.getName())
                .masterId(cat.getMaster().getId())
                .birthday(cat.getBirthday())
                .build();
    }

    public void delete(Integer id) {
        catService.delete(id);
    }

    public void setFriends(SetUnsetFriendshipRequest request) {
        catService.setFriends(request.getCatIdFirst(), request.getCatIdSecond());
    }

    public void unsetFriends(SetUnsetFriendshipRequest request) {
        catService.deleteFriendship(request.getCatIdFirst(), request.getCatIdSecond());
    }

    public void changeMaster(ChangeMasterRequest request) {
        catService.changeMaster(request.getCatId(), request.getNewMasterId());
    }
}
