package com.task.akkahttp.warehouse;

import com.task.akkahttp.material.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WarehouseService {

  public final static List<Warehouse> WAREHOUSES = new ArrayList<>();


  static {
    WAREHOUSES.add(new Warehouse(1L, "Warehouse 1", mockComponent()));
    WAREHOUSES.add(new Warehouse(2L, "Warehouse 2", mockComponent()));
    WAREHOUSES.add(new Warehouse(3L, "Warehouse 3", mockComponent()));
  }

  public Optional<Warehouse> getUser(Long id) {
    return WAREHOUSES.stream()
            .filter(warehouse -> warehouse.getId()
                    .equals(id))
            .findFirst();
  }

  public void createUser(Warehouse warehouse) {
    WAREHOUSES.add(warehouse);
  }

  public static List<Component> mockComponent() {
    List<Component> list = new ArrayList<Component>();
    list.add(new Component(1L, "c1", 100));
    list.add(new Component(2L, "c2", 300));
    list.add(new Component(3L, "c3", 200));

    return list;
  }

  public List<Warehouse> getUsers(){
    return WAREHOUSES;
  }

}
