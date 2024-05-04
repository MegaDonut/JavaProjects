package org.itmo.servicelayer2.services;

import lombok.RequiredArgsConstructor;
import org.itmo.dataaccesslayer2.pojo.User;
import org.itmo.dataaccesslayer2.pojo.UserDetail;
import org.itmo.dataaccesslayer2.repositories.UserRepository;
import org.itmo.servicelayer2.services.model.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetail(repository.findUserByLogin(username));
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(encoder().encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setMaster(request.getMaster());

        user = repository.save(user);
        return user;
    }

    public void deleteUser(String username) {
        repository.deleteUserByLogin(username);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

