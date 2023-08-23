package com.sv.io.repository;

import com.sv.io.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsCsvImportRepository extends JpaRepository<OrderDetails, Long> {

}
