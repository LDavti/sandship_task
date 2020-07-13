package com.task.akkahttp.user;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;
import com.task.akkahttp.warehouse.Warehouse;

import java.util.Optional;


public class UserActor extends AbstractActor {

    private UserService userService = new UserService();

    public static Props props() {
        return Props.create(UserActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UserMessages.CreateUserMessage.class, handleCreateUser())
                .match(UserMessages.GetUserMessage.class, handleGetUser())
                .build();
    }

    private FI.UnitApply<UserMessages.CreateUserMessage> handleCreateUser() {
        return createUserMessageMessage -> {
            userService.createUser(createUserMessageMessage.getUser());
            sender().tell(new UserMessages.ActionPerformed(String.format("User %s created.", createUserMessageMessage.getUser()
                    .getName())), getSelf());
        };
    }

    private FI.UnitApply<UserMessages.GetUserMessage> handleGetUser() {
        return getUserMessageMessage -> {
            sender().tell(userService.getUser(getUserMessageMessage.getUserId()), getSelf());
        };
    }


    private FI.UnitApply<UserMessages.GetUserMessage> handleMoveComponent() {
        return getUserMessageMessage -> {
            Optional<User> user = userService.getUser(getUserMessageMessage.getUserId());
            if (user.isPresent()) {
                Optional<Warehouse> w1 = user.get().getWarehouse().stream()
                        .filter(sw1 -> sw1.getId()
                                .equals(getUserMessageMessage.getW1Id()))
                        .findFirst();
                Optional<Warehouse> w2 = user.get().getWarehouse().stream()
                        .filter(sw1 -> sw1.getId()
                                .equals(getUserMessageMessage.getW1Id()))
                        .findFirst();
                if (w1.isPresent() && w2.isPresent()) {
                    sender().tell(userService.moveComponents(user.get(), w1.get(), w2.get()), getSelf());

                }

            }
        };
    }

}
