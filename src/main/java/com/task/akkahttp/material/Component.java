package com.task.akkahttp.material;

public class Component {

    private final Long id;
    private int count;
    private final String name;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int c) {
        count += c;
    }

    public void removeCount(int c) {
        count-=c;
    }



    public Component(int count) {
        this.count = count;
        this.name = "";
        this.id = null;
    }

    public Component(Long id, String name, int count) {
        this.name = name;
        this.id = id;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
