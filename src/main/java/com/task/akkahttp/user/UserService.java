package com.task.akkahttp.user;

import com.task.akkahttp.material.Component;
import com.task.akkahttp.warehouse.Warehouse;
import com.task.akkahttp.warehouse.WarehouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserService {

    private final static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "Alice", mockWarehouse()));
        users.add(new User(2L, "Bob", mockWarehouse()));
        users.add(new User(3L, "Chris", mockWarehouse()));
    }

    public Optional<User> getUser(Long id) {
        return users.stream()
                .filter(user -> user.getId()
                        .equals(id))
                .findFirst();
    }

    /**
     *
     * generate mock data
     */
    private static List<Warehouse> mockWarehouse() {
        List<Warehouse> list = new ArrayList<Warehouse>();
        list.add(new Warehouse(1L, "c1", WarehouseService.mockComponent()));
        list.add(new Warehouse(2L, "c2", WarehouseService.mockComponent()));
        list.add(new Warehouse(3L, "c3", WarehouseService.mockComponent()));

        return list;
    }

    public void createUser(User user) {
        users.add(user);
    }

    public Optional<Component> moveComponents(User u, Warehouse w1, Warehouse w2/*, Component cm*/) {
        getWarehouse(u.getId(),  w1.getId() ) ;
        getWarehouse(u.getId(),  w2.getId() ) ;
        return null;
    }

    public Warehouse getWarehouse (long userId, long warehouseIDd) {
        return UserService.users.get((int)userId).getWarehouse().get((int)warehouseIDd);
    }
    public List<User> getUsers() {
        return users;
    }

}
