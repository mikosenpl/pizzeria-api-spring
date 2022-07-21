package pl.pizzeria.pizzeria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pizzeria.pizzeria.models.Pizza;

import java.util.UUID;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, UUID> {
}
