package com.restaurant.tableservice.controller;

import com.restaurant.tableservice.model.RestaurantTable;
import com.restaurant.tableservice.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class TableController {

    private final TableService tableService;

    @GetMapping("/api/tables")
    public List<RestaurantTable> getTables() {
        return tableService.findAllTables();
    }


    @GetMapping("/api/tables/{id}")
    public RestaurantTable getTable(@PathVariable int id) {
        return tableService.findTableById(id);
    }


    @PostMapping("/api/tables")
    public RestaurantTable createTable(@RequestBody RestaurantTable restaurantTable) {
        return tableService.createTable(restaurantTable);
    }



    @PutMapping("/api/tables/{id}")
    public RestaurantTable updateTable(@PathVariable long id, @RequestBody RestaurantTable restaurantTable) {
        return tableService.updateTable(id, restaurantTable);
    }


    @PatchMapping("/api/tables/{id}/toggle-occupancy")
    public RestaurantTable toggleOccupancy(@PathVariable long id) {
        return tableService.toggleTableOccupancy(id);
    }


    @DeleteMapping("/api/tables/{id}")
    public void deleteTable(@PathVariable long id) {
        tableService.deleteTable(id);
    }

}
