package org.itmo.application2.controllers;

import lombok.RequiredArgsConstructor;
import org.itmo.servicelayer2.services.UserDetailService;
import org.itmo.servicelayer2.services.mapper.Mapper;
import org.itmo.servicelayer2.services.model.CreateShowUserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserDetailService service;
    private final Mapper mapper;

    @GetMapping("/{login}")
    public CreateShowUserResponse showMaster(@PathVariable String login) {
        UserDetails user = service.loadUserByUsername(login);

        return mapper.toCreateShowUserResponse(user);
    }

    @DeleteMapping("/{login}")
    public void deleteMaster(@PathVariable String login) {
        service.deleteUser(login);
    }
}