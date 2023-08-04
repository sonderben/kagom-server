package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity,Long> {
}
