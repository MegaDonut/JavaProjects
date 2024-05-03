package org.itmo.servicelayer2.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer2.pojo.Master;
import org.itmo.dataaccesslayer2.pojo.User;
import org.itmo.dataaccesslayer2.repositories.MasterRepository;
import org.itmo.servicelayer2.services.model.CreateMasterRequest;
import org.itmo.servicelayer2.services.model.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;
    private final UserDetailService service;

    public Master create(CreateMasterRequest request) {
        Master master = new Master();
        master.setBirthday(request.getBirthday());
        master.setName(request.getName());

        CreateUserRequest request1 = CreateUserRequest.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .role(request.getRole())
                .master(master)
                .build();

        User user = service.createUser(request1);
        master.setUser(user);

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
