package pl.pizzeria.pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pizzeria.pizzeria.models.Order;


import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
