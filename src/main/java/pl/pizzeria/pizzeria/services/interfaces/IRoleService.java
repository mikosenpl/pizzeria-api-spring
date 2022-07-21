package pl.pizzeria.pizzeria.services.interfaces;

import pl.pizzeria.pizzeria.models.Role;

import java.util.Optional;

public interface IRoleService extends IService<Role>{
    Optional<Role> findByName(String name);
}
