package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.entity.Order;
import kz.bloooom.administration.domain.entity.Subscription;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    List<Order> findAll();

    List<Order> findAllBySubscription(Subscription subscription);

    List<Order> saveAll(List<Order> orders);
}
