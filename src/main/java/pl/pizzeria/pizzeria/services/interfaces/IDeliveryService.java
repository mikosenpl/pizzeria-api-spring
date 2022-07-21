package pl.pizzeria.pizzeria.services.interfaces;

import pl.pizzeria.pizzeria.models.Delivery;

import java.util.Optional;

public interface IDeliveryService extends IService<Delivery>{
    Optional<Delivery> findByDeliveryStatus(Boolean deliveryStatus);

}
