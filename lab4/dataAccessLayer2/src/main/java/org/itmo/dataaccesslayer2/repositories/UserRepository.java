package org.itmo.dataaccesslayer2.repositories;

import org.itmo.dataaccesslayer2.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    void deleteUserByLogin(String login);
}