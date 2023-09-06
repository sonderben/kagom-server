package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.PackageEntity;
import com.sonderben.kagom.entity.ShipmentEntity;
import com.sonderben.kagom.repository.AddressRepository;
import com.sonderben.kagom.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService  {
    @Autowired
    PackageRepository repository;


    public List<PackageEntity> getAll() {
        return repository.findAll();
    }


    public PackageEntity getOneById( Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<PackageEntity> findPackagesByShipment(Long id){
        List<PackageEntity> packagesEntity = repository.findPackagesByShipmentId( id );
        return packagesEntity;
    }

}