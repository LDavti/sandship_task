package com.task.akkahttp.material;

import java.io.Serializable;

public interface ComponentMessages {

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
    private final Component component;

    public CreateUserMessage(Component component) {
      this.component = component;
    }

    public Component getComponent() {
      return component;
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
