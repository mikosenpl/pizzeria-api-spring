package pl.pizzeria.pizzeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pizzeria.pizzeria.models.Client;
import pl.pizzeria.pizzeria.repositories.ClientRepository;
import pl.pizzeria.pizzeria.services.interfaces.IClientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService implements IClientService {

    @Autowired
    ClientRepository repository;


    @Override
    public Optional<Client> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Client save(Client element) {
        return repository.save(element);
    }

    @Override
    public Client update(Client element) {
        return repository.save(element);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }
}
