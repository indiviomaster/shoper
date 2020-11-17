package ru.indivio.market.repositories;

import ru.indivio.market.entites.DeliveryAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findAllByUserId(Long userId);
}
