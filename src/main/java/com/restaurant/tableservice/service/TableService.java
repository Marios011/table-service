package com.restaurant.tableservice.service;

import com.restaurant.tableservice.model.RestaurantTable;
import com.restaurant.tableservice.repository.TableRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final RabbitTemplate rabbitTemplate;

    public TableService(TableRepository tableRepository, RabbitTemplate rabbitTemplate) {
        this.tableRepository = tableRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


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
            RestaurantTable updatedTable = tableRepository.save(table);
            rabbitTemplate.convertAndSend("table-status-queue", "Table " + id + " status updated");
            return updatedTable;
        }
        return null;
    }


    public void deleteTable(long id) {
        tableRepository.deleteById(id);
    }



}
