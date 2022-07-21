package pl.pizzeria.pizzeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pizzeria.pizzeria.models.Order;
import pl.pizzeria.pizzeria.repositories.OrderRepository;
import pl.pizzeria.pizzeria.services.interfaces.IOrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order element) {
        return orderRepository.save(element);
    }

    @Override
    public Order update(Order element) {
        return orderRepository.save(element);
    }

    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
