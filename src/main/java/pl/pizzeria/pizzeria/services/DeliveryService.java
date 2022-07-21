package pl.pizzeria.pizzeria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pizzeria.pizzeria.models.Delivery;
import pl.pizzeria.pizzeria.repositories.DeliveryRepository;
import pl.pizzeria.pizzeria.services.interfaces.IDeliveryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService implements IDeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;


    @Override
    public Optional<Delivery> findByDeliveryStatus(Boolean deliveryStatus) {
        return deliveryRepository.findByDeliveryStatus(deliveryStatus);
    }

    @Override
    public Optional<Delivery> findById(UUID id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public Delivery save(Delivery element) {
        return deliveryRepository.save(element);
    }

    @Override
    public Delivery update(Delivery element) {
        return deliveryRepository.save(element);
    }

    @Override
    public void delete(UUID id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }
}
