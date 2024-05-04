package org.itmo.dataaccesslayer2.repositories;

import org.itmo.dataaccesslayer2.pojo.Cat;
import org.itmo.dataaccesslayer2.pojo.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    Cat findCatById(Integer id);
    List<Cat> findCatsByMaster(Master master);
}
