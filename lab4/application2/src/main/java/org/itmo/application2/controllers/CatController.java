package org.itmo.application2.controllers;


import lombok.RequiredArgsConstructor;
import org.itmo.servicelayer2.services.mapper.Mapper;
import org.itmo.servicelayer2.services.model.*;
import org.itmo.dataaccesslayer2.pojo.Cat;
import org.itmo.servicelayer2.services.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findAll")
    public List<CreateShowCatResponse> findAll() {
        return catService.findCats().stream().map(mapper::toCreateShowCatResponse).toList();
    }

    @GetMapping("/filter")
    public List<CreateShowCatResponse> filter(FilterCatRequest request) {
        return catService.filterCats(request).stream().map(mapper::toCreateShowCatResponse).toList();
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
