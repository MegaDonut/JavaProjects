package org.itmo.application1.controllers;

import lombok.RequiredArgsConstructor;
import org.itmo.application1.model.CreateMasterRequest;
import org.itmo.application1.model.CreateShowMasterResponse;
import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.servicelayer1.services.MasterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {
    private final MasterService masterService;
    @PostMapping("/create")
    public CreateShowMasterResponse createMaster(@RequestBody CreateMasterRequest request) {
        Master master = masterService.create(request.getBirthday(), request.getName());
        return CreateShowMasterResponse
                .builder()
                .id(master.getId())
                .name(master.getName())
                .birthday(master.getBirthday())
                .build();
    }

    @GetMapping("/{id}")
    public CreateShowMasterResponse showMaster(@PathVariable Integer id) {
        Master master = masterService.show(id);
        return CreateShowMasterResponse
                .builder()
                .id(master.getId())
                .name(master.getName())
                .birthday(master.getBirthday())
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable Integer id) {
        masterService.delete(id);
    }
}
