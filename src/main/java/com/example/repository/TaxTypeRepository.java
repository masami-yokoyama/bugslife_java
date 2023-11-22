package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.TaxType;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

}
