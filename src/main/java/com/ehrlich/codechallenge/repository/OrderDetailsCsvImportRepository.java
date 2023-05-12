package com.ehrlich.codechallenge.repository;

import com.ehrlich.codechallenge.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsCsvImportRepository extends JpaRepository<OrderDetails, Long> {

}
