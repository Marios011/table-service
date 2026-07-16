package com.restaurant.tableservice.repository;

import com.restaurant.tableservice.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}
