package com.task.akkahttp.user;

import java.io.Serializable;

public interface UserMessages {

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
    private final User user;

    public CreateUserMessage(User user) {
      this.user = user;
    }

    public User getUser() {
      return user;
    }
  }

  class GetUserMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long userId;
    private final Long w1Id;
    private final Long w2Id;
    private final Long cId;

    public GetUserMessage(Long userId, Long w1Id, Long w2Id, Long cId) {
      this.userId = userId;
      this.w1Id = w1Id;
      this.w2Id = w2Id;
      this.cId = cId;
    }

    public Long getUserId() {
      return userId;
    }
    public Long getW1Id() {
      return w1Id;
    }
    public Long getW2Id() {
      return w2Id;
    }
    public Long getCId() {
      return cId;
    }
  }

}
