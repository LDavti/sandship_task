package com.task.akkahttp.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComponentService {

  private final static List<Component> COMPONENTS = new ArrayList<>();

  static {
    COMPONENTS.add(new Component(1L, "Iron", 100));
    COMPONENTS.add(new Component(2L, "Cupper", 200));
    COMPONENTS.add(new Component(3L, "Ferum", 300));

  }

  public Optional<Component> getUser(Long id) {
    return COMPONENTS.stream()
            .filter(component -> component.getId()
                    .equals(id))
            .findFirst();
  }

  public void createUser(Component component) {
    COMPONENTS.add(component);
  }

  public List<Component> getUsers(){
    return COMPONENTS;
  }

}
