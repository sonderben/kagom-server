package com.sonderben.kagom.repository;

import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT  new EmployeeEntity ( c.id, c.firsName, c.lastName, c.email, c.telephone, c.birthday,c.jobTitle, c.KMIdentity) FROM EmployeeEntity c where c.id = :id")
    EmployeeEntity findEmployee(@Param("id") Long id);
}
