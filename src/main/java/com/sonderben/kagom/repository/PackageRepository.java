package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {


    @Query("SELECT p FROM ShipmentEntity s JOIN s.KMPackage p WHERE s.id = :shipmentId")
    public List<PackageEntity> findPackagesByShipmentId(@Param("shipmentId") Long shipmentId);
}
