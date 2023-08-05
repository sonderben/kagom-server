package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends org.springframework.data.jpa.repository.JpaRepository<AddressEntity, Long> {
}
