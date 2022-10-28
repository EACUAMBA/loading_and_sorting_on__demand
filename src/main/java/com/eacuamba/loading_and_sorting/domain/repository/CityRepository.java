package com.eacuamba.loading_and_sorting.domain.repository;

import com.eacuamba.loading_and_sorting.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
