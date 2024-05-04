package org.itmo.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dao.MasterDao;
import org.itmo.pojo.Master;

import java.util.Date;

@RequiredArgsConstructor
public class MasterService {
    final MasterDao masterDao;

    public Master create(Date birthday, String name) {
        Master master = new Master();
        master.setBirthday(birthday);
        master.setName(name);
        master = masterDao.save(master);
        return master;
    }

    public Master show(Integer masterId) {
        return masterDao.findById(masterId);
    }

    public void delete(Integer masterId) {
        masterDao.delete(masterDao.findById(masterId));
    }
}
