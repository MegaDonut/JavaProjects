package org.itmo.application2.controllers;

import lombok.RequiredArgsConstructor;
import org.itmo.servicelayer2.services.mapper.Mapper;
import org.itmo.servicelayer2.services.model.CreateMasterRequest;
import org.itmo.servicelayer2.services.model.CreateShowMasterResponse;
import org.itmo.dataaccesslayer2.pojo.Master;
import org.itmo.servicelayer2.services.MasterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class MasterController {
    private final MasterService masterService;
    private final Mapper mapper;
    @PostMapping("/create")
    public CreateShowMasterResponse createMaster(@RequestBody CreateMasterRequest request) {
        Master master = masterService.create(request);
        return mapper.toCreateShowMasterResponse(master);
    }

    @GetMapping("/{id}")
    public CreateShowMasterResponse showMaster(@PathVariable Integer id) {
        Master master = masterService.show(id);
        return mapper.toCreateShowMasterResponse(master);
    }

    @DeleteMapping("/{id}")
    public void deleteMaster(@PathVariable Integer id) {
        masterService.delete(id);
    }
}
