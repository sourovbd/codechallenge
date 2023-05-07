package com.ehrlich.codechallenge.repository;

import com.ehrlich.codechallenge.Entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaCsvImportRepository extends JpaRepository<Pizza, Long> {

}
