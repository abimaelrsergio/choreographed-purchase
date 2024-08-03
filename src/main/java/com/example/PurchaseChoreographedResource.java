package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("purchase-choreographed")
public class PurchaseChoreographedResource {
    @Inject
    private OrderService orderService;

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga() {
        long id = 0L;
        orderService.doOrder(++id, 20);
        orderService.doOrder(++id, 30);
        orderService.doOrder(++id, 30);
        orderService.doOrder(++id, 25);
        return Response.ok().build();
    }
}
