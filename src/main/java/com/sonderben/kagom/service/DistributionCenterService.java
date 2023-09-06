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
        List<DistributionCenterEntity> ces = repository.findAll();
        for (DistributionCenterEntity ce:ces){
            ce.getScheduleString();
            ce.setSchedules(null);
        }

        return ces;
    }


    public DistributionCenterEntity findById(Long id) {
        DistributionCenterEntity ce = repository.findById(id).orElse(null);
        if (ce !=null)
            ce.getScheduleString();
        return ce;
    }

    public DistributionCenterEntity save(DistributionCenterEntity dce){

        return repository.save(dce);

    }
}