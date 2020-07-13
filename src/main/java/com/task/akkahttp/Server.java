package com.task.akkahttp;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.task.akkahttp.material.ComponentActor;
import com.task.akkahttp.user.User;
import com.task.akkahttp.user.UserActor;
import com.task.akkahttp.user.UserMessages;
import com.task.akkahttp.warehouse.WarehouseActor;
import scala.concurrent.duration.Duration;

import static akka.http.javadsl.server.PathMatchers.*;

class Server extends HttpApp {

    private final ActorRef userActor;
    private final ActorRef warehouseActor;
    private final ActorRef componentActor;

    Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));

    Server(ActorRef userActor, ActorRef warehouseActor,ActorRef componentActor) {
        this.userActor = userActor;
        this.warehouseActor= warehouseActor;
        this.componentActor = componentActor;
    }

    @Override
    public Route routes() {
        return path("users", this::postUser)
                .orElse(path(segment("users").slash(longSegment()), id ->
                        route(getUser(id))))
                .orElse(path(segment("users").slash(longSegment()), id ->
                        route(moveComponent(id))));
    }

    private Route getUser(Long id, Long w1Id, Long w2Id, Long cId) {
        return get(() -> {
            CompletionStage<Optional<User>> user = PatternsCS.ask(userActor, new UserMessages.GetUserMessage(id, w1Id, w2Id, cId), timeout)
                    .thenApply(obj -> (Optional<User>) obj);

            return onSuccess(() -> user, performed -> {
                if (performed.isPresent())
                    return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                else
                    return complete(StatusCodes.NOT_FOUND);
            });
        });
    }

    /**
     *
     * move component from one warehouse to another, haven't finished yet :(
     */
    private Route moveComponent(Long id) {
        return put(() -> {
            CompletionStage<Optional<User>> user = PatternsCS.ask(userActor, new UserMessages.GetUserMessage(id, w1Id, w2Id, cId), timeout)
                    .thenApply(obj -> (Optional<User>) obj);

            return onSuccess(() -> user, performed -> {
                if (performed.isPresent())
                    return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                else
                    return complete(StatusCodes.NOT_FOUND);
            });
        });
    }

    private Route postUser() {
        return route(post(() -> entity(Jackson.unmarshaller(User.class), user -> {
            CompletionStage<UserMessages.ActionPerformed> userCreated = PatternsCS.ask(userActor, new UserMessages.CreateUserMessage(user), timeout)
                    .thenApply(obj -> (UserMessages.ActionPerformed) obj);

            return onSuccess(() -> userCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("Server");
        ActorRef userActor = system.actorOf(UserActor.props(), "userActor");
        ActorRef warehouseActor = system.actorOf(WarehouseActor.props(), "warehouseActor");
        ActorRef componentActor = system.actorOf(ComponentActor.props(), "componentActor");
        Server server = new Server(userActor,warehouseActor,componentActor);
        server.startServer("localhost", 8080, system);
    }

}
