package com.restaurant.tableservice.service;

import com.restaurant.tableservice.model.RestaurantTable;
import com.restaurant.tableservice.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;


    public List<RestaurantTable> findAllTables() {
        return tableRepository.findAll();
    }


    public RestaurantTable findTableById(long id) {
        return tableRepository.findById(id).orElse(null);
    }


    public RestaurantTable createTable(RestaurantTable restaurantTable) {
        return tableRepository.save(restaurantTable);
    }


    public RestaurantTable updateTable(long id, RestaurantTable restaurantTable) {
        restaurantTable.setId(id);
        return tableRepository.save(restaurantTable);
    }

    public RestaurantTable toggleTableOccupancy(long id) {
        RestaurantTable table = findTableById(id);
        if (table != null) {
            table.setIsOccupied(!table.getIsOccupied());
            return tableRepository.save(table);
        }
        return null;
    }


    public void deleteTable(long id) {
        tableRepository.deleteById(id);
    }


}
