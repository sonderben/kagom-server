package com.sonderben.kagom.service;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import com.sonderben.kagom.repository.DistributionCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionCenterService  {

    @Autowired
    DistributionCenterRepository repository;


    public List<DistributionCenterEntity> findAll() {
        return repository.findAll();
    }


    public DistributionCenterEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DistributionCenterEntity save(DistributionCenterEntity dce){
        return repository.save(dce);

    }
}