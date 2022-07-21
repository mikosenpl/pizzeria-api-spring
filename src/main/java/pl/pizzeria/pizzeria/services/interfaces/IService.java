package pl.pizzeria.pizzeria.services.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IService<T> {
    Optional<T> findById(UUID id);

    T save(T element);

    T update(T element);

    void delete(UUID id);

    List<T> findAll();
}
