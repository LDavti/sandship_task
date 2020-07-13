package com.task.akkahttp.material;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;


public class ComponentActor extends AbstractActor {

  private ComponentService componentService = new ComponentService();

  public static Props props() {
    return Props.create(ComponentActor.class);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
            .match(ComponentMessages.CreateUserMessage.class, handleCreateUser())
            .match(ComponentMessages.GetUserMessage.class, handleGetUser())
            .build();
  }

  private FI.UnitApply<ComponentMessages.CreateUserMessage> handleCreateUser() {
    return createUserMessageMessage -> {
      componentService.createUser(createUserMessageMessage.getComponent());
      sender().tell(new ComponentMessages.ActionPerformed(String.format("User %s created.", createUserMessageMessage.getComponent()
              .getName())), getSelf());
    };
  }

  private FI.UnitApply<ComponentMessages.GetUserMessage> handleGetUser() {
    return getUserMessageMessage -> {
      sender().tell(componentService.getUser(getUserMessageMessage.getUserId()), getSelf());
    };
  }

}
