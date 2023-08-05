package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.DistributionCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionCenterRepository extends JpaRepository<DistributionCenterEntity, Long> {
}
