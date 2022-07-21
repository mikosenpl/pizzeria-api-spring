package pl.pizzeria.pizzeria.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pizzeria.pizzeria.models.Role;
import pl.pizzeria.pizzeria.repositories.RoleRepository;
import pl.pizzeria.pizzeria.services.interfaces.IRoleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role element) {
        return roleRepository.save(element);
    }

    @Override
    public Role update(Role element) {
        return roleRepository.save(element);
    }

    @Override
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
