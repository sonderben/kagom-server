package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity,Long> {

    List<ShipmentEntity> findByReceiver(CustomerEntity receiver);

    List<ShipmentEntity> findBySender(CustomerEntity sender);
}
