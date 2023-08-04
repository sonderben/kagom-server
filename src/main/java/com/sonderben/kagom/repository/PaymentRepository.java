package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<AddressEntity,Long> {
}
