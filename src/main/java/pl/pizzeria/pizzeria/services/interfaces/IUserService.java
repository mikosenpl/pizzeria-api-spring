package pl.pizzeria.pizzeria.services.interfaces;

import pl.pizzeria.pizzeria.models.User;

import java.util.Optional;

public interface IUserService extends IService<User>{
    Optional<User> findByEmail(String email);
}
