package com.task.akkahttp.user;

import com.task.akkahttp.warehouse.Warehouse;

import java.util.List;

public class User {

    private final Long id;
    private final String name;

    private  List<Warehouse> warehouse;

    public List<Warehouse> getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(List<Warehouse> warehouse) {
        this.warehouse = warehouse;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouse.add(warehouse);
    }


    public void removeWarehouse(Warehouse warehouse) {
        this.warehouse.remove(warehouse);
    }

    public User(List<Warehouse> warehouse) {
        this.warehouse = warehouse;
        this.name = "";
        this.id = null;
    }

    public User(Long id, String name, List<Warehouse> warehouse) {
        this.name = name;
        this.id = id;
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
