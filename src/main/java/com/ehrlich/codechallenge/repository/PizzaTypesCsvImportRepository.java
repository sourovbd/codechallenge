package com.ehrlich.codechallenge.repository;

import com.ehrlich.codechallenge.Entity.PizzaTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaTypesCsvImportRepository extends JpaRepository<PizzaTypes, Long> {

    PizzaTypes findByPizzaTypeId(String pizzaTypeId);
}
