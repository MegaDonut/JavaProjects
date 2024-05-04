package org.itmo.dataaccesslayer2.repositories;

import org.itmo.dataaccesslayer2.pojo.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Integer> {
    Master findMasterById(Integer id);
}