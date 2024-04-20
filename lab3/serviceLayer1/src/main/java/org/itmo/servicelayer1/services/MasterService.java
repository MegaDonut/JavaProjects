package org.itmo.servicelayer1.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.itmo.servicelayer1.services.model.CreateMasterRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;

    public Master create(CreateMasterRequest request) {
        Master master = new Master();
        master.setBirthday(request.getBirthday());
        master.setName(request.getName());
        master = masterRepository.save(master);
        return master;
    }

    public Master show(Integer masterId) {
        return masterRepository.findMasterById(masterId);
    }

    public void delete(Integer masterId) {
        masterRepository.deleteById(masterId);
    }
}
