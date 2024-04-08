package org.itmo.servicelayer1.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer1.pojo.Master;
import org.itmo.dataaccesslayer1.repositories.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;

    public Master create(Date birthday, String name) {
        Master master = new Master();
        master.setBirthday(birthday);
        master.setName(name);
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
