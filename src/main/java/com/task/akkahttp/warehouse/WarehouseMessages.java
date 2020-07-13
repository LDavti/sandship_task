package com.task.akkahttp.warehouse;

import java.io.Serializable;

public interface WarehouseMessages {

  class ActionPerformed implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String description;

    public ActionPerformed(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }

  class CreateUserMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Warehouse warehouse;

    public CreateUserMessage(Warehouse warehouse) {
      this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
      return warehouse;
    }
  }

  class GetUserMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long userId;

    public GetUserMessage(Long userId) {
      this.userId = userId;
    }

    public Long getUserId() {
      return userId;
    }
  }

}
