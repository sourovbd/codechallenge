package com.sv.io.repository;

import com.sv.io.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersCsvImportRepository extends JpaRepository<Orders, Long> {

    Orders findByOrderId(Long orderId);
}
