package com.tiagop;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @GET
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello from RESTEasy Reactive");
    }
}