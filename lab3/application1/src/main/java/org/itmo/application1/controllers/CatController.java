package org.itmo.application1.controllers;


import lombok.RequiredArgsConstructor;
import org.itmo.servicelayer1.services.mapper.Mapper;
import org.itmo.servicelayer1.services.model.ChangeMasterRequest;
import org.itmo.servicelayer1.services.model.CreateCatRequest;
import org.itmo.servicelayer1.services.model.CreateShowCatResponse;
import org.itmo.servicelayer1.services.model.SetUnsetFriendshipRequest;
import org.itmo.dataaccesslayer1.pojo.Cat;
import org.itmo.servicelayer1.services.CatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;
    private final Mapper mapper;

    @PostMapping("/create")
    public CreateShowCatResponse createCat(@RequestBody CreateCatRequest request) {
        Cat cat = catService.create(request);

        return mapper.toCreateShowCatResponse(cat);
    }

    @GetMapping("/{id}")
    public CreateShowCatResponse showCat(@PathVariable Integer id) {
        Cat cat = catService.show(id);

        return mapper.toCreateShowCatResponse(cat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        catService.delete(id);
    }

    @PatchMapping("/setFriends")
    public void setFriends(@RequestBody SetUnsetFriendshipRequest request) {
        catService.setFriends(request);
    }

    @PatchMapping("/unsetFriends")
    public void unsetFriends(@RequestBody SetUnsetFriendshipRequest request) {
        catService.deleteFriendship(request);
    }

    @PatchMapping("/changeMaster")
    public void changeMaster(@RequestBody ChangeMasterRequest request) {
        catService.changeMaster(request);
    }
}
