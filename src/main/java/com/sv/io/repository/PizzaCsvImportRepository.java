package com.sv.io.repository;

import com.sv.io.Entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaCsvImportRepository extends JpaRepository<Pizza, Long> {

}
