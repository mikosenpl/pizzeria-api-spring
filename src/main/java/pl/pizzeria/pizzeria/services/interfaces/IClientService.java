package pl.pizzeria.pizzeria.services.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import pl.pizzeria.pizzeria.models.Client;

public interface IClientService extends IService<Client> {

    Optional<Client> findByEmail(String email);
}
