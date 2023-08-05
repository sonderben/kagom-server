package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("SELECT  new CustomerEntity ( c.id, c.firsName, c.lastName, c.email, c.telephone, c.birthday, c.KMIdentity) FROM CustomerEntity c where c.id = :id")
    CustomerEntity findCustomer(@Param("id") Long id);
}
