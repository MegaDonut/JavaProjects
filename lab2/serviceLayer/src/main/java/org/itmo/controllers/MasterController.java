package org.itmo.controllers;

import org.itmo.dao.MasterDao;
import org.itmo.model.CreateMasterRequest;
import org.itmo.model.CreateShowMasterResponse;
import org.itmo.pojo.Master;
import org.itmo.services.MasterService;

public class MasterController {
    MasterDao masterDao = new MasterDao();
    MasterService masterService = new MasterService(masterDao);
    public CreateShowMasterResponse createMaster(CreateMasterRequest request) {
        Master master = masterService.create(request.getBirthday(), request.getName());
        return CreateShowMasterResponse
                .builder()
                .id(master.getId())
                .name(master.getName())
                .birthday(master.getBirthday())
                .build();
    }

    public CreateShowMasterResponse showMaster(Integer id) {
        Master master = masterService.show(id);
        return CreateShowMasterResponse
                .builder()
                .id(master.getId())
                .name(master.getName())
                .birthday(master.getBirthday())
                .build();
    }

    public void deleteMaster(Integer id) {
        masterService.delete(id);
    }
}
