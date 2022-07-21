package pl.pizzeria.pizzeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pizzeria.pizzeria.models.Pizza;
import pl.pizzeria.pizzeria.repositories.PizzaRepository;
import pl.pizzeria.pizzeria.services.interfaces.IPizzaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PizzaService implements IPizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public Optional<Pizza> findById(UUID id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public Pizza save(Pizza element) {
        return pizzaRepository.save(element);
    }

    @Override
    public Pizza update(Pizza element) {
        return pizzaRepository.save(element);
    }

    @Override
    public void delete(UUID id) {
        pizzaRepository.deleteById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
}
