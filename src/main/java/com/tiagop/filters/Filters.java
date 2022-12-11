package com.tiagop.filters;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.keycloak.admin.client.Keycloak;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

public class Filters {
    @Inject
    Keycloak keycloak;

    @ServerRequestFilter
    public Uni<Response> filter(ContainerRequestContext requestContext) {
        return Uni.createFrom().item(() ->
                keycloak.realm("quarkus")
                        .users()
                        .search("someuser")
                        .stream()
                        .findFirst()
                        .orElseThrow()
                        .firstAttribute("the-attribute")).map(attr -> {
            if (attr.equals("some-value")) {
                return null;
            }
            return Response.status(403).build();
        });
    }
}

