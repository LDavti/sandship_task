package com.task.akkahttp.warehouse;

import com.task.akkahttp.material.Component;

import java.util.List;

public class Warehouse {

    private final Long id;

    private final String name;
    private List<Component> component;

    public List<Component> getComponent() {
        return component;
    }

    public void setComponent(List<Component> component) {
        this.component = component;
    }

    public void addComponent(Component component) {
        this.component.add(component);
    }
    public void removeComponent(Component component) {
        this.component.remove(component);
    }


    public Warehouse(List<Component> component) {
        this.component = component;
        this.name = "";
        this.id = null;
    }

    public Warehouse(Long id, String name, List<Component> component) {
        this.name = name;
        this.id = id;
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
