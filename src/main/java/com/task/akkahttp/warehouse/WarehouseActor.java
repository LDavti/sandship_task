package com.task.akkahttp.warehouse;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;


public class WarehouseActor extends AbstractActor {

  private WarehouseService warehouseService = new WarehouseService();

  public static Props props() {
    return Props.create(WarehouseActor.class);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
            .match(WarehouseMessages.CreateUserMessage.class, handleCreateUser())
            .match(WarehouseMessages.GetUserMessage.class, handleGetUser())
            .build();
  }

  private FI.UnitApply<WarehouseMessages.CreateUserMessage> handleCreateUser() {
    return createUserMessageMessage -> {
      warehouseService.createUser(createUserMessageMessage.getWarehouse());
      sender().tell(new WarehouseMessages.ActionPerformed(String.format("User %s created.", createUserMessageMessage.getWarehouse()
              .getName())), getSelf());
    };
  }

  private FI.UnitApply<WarehouseMessages.GetUserMessage> handleGetUser() {
    return getUserMessageMessage -> {
      sender().tell(warehouseService.getUser(getUserMessageMessage.getUserId()), getSelf());
    };
  }

}
