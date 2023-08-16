package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity,Long> {

    List<ShipmentEntity> findByReceiver(CustomerEntity receiver);

    List<ShipmentEntity> findBySender(CustomerEntity sender);

    ShipmentEntity findByTrackingId(String trackingId);

    @Query("select s from ShipmentEntity s where s.sender.id = :id and s.payment.balance = 0")
    List<ShipmentEntity>findShipmentPaidBySender( @Param("id") long id );

    @Query("select s from ShipmentEntity s where s.sender.id = :id and s.payment.balance > 0")
    List<ShipmentEntity>findShipmentDueBySender( @Param("id") long id );



    @Query("select s from ShipmentEntity s where s.receiver.id = :id and s.payment.balance = 0")
    List<ShipmentEntity>findShipmentPaidByReceiver( @Param("id") long id );

    @Query("select s from ShipmentEntity s where s.receiver.id = :id and s.payment.balance > 0")
    List<ShipmentEntity>findShipmentDueByReceiver( @Param("id") long id );
}
