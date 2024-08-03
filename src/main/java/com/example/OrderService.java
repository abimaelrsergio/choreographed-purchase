package com.example;

import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

    @Inject
    private CreditService creditService;

    private final Set<Long> orders = new HashSet<>();

    public void doOrder(Long id, int value) {
        orders.add(id);
        try {
            creditService.newOrderValue(id, value);
            System.out.println("Order " + id + ", Registered. Value = " + value + ". Balance available = " + creditService.getCreditTotal());
        } catch (IllegalStateException e) {
            undoOrder(id);
            System.err.println("Order " + id + ", reversed. Value = " + value);
        }
    }

    public void undoOrder(Long id){
        orders.remove(id);
    }
}
