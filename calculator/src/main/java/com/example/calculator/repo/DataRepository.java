package com.example.calculator.repo;

import com.example.calculator.models.Data;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Long> {
}
