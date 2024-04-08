package org.itmo.application1.controllers;


import lombok.RequiredArgsConstructor;
import org.itmo.application1.model.ChangeMasterRequest;
import org.itmo.application1.model.CreateCatRequest;
import org.itmo.application1.model.CreateShowCatResponse;
import org.itmo.application1.model.SetUnsetFriendshipRequest;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.servicelayer1.services.CatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;

    @PostMapping("/create")
    public CreateShowCatResponse createCat(@RequestBody CreateCatRequest request) {
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

    @GetMapping("/{id}")
    public CreateShowCatResponse showCat(@PathVariable Integer id) {
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        catService.delete(id);
    }

    @PatchMapping("/setFriends")
    public void setFriends(@RequestBody SetUnsetFriendshipRequest request) {
        catService.setFriends(request.getCatIdFirst(), request.getCatIdSecond());
    }

    @PatchMapping("/unsetFriends")
    public void unsetFriends(@RequestBody SetUnsetFriendshipRequest request) {
        catService.deleteFriendship(request.getCatIdFirst(), request.getCatIdSecond());
    }

    @PatchMapping("/changeMaster")
    public void changeMaster(@RequestBody ChangeMasterRequest request) {
        catService.changeMaster(request.getCatId(), request.getNewMasterId());
    }
}
