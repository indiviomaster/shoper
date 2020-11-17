package ru.indivio.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.indivio.market.entites.DeliveryAddress;
import ru.indivio.market.repositories.DeliveryAddressRepository;

import java.util.List;

@Service
public class DeliveryAddressService {
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    public void setDeliveryAddressRepository(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<DeliveryAddress> getUserAddresses(Long userId) {
        return deliveryAddressRepository.findAllByUserId(userId);
    }

    public void save(DeliveryAddress deliveryAddress) {
        deliveryAddressRepository.save(deliveryAddress);
    }

    public List<DeliveryAddress> getAll() {
        return (List<DeliveryAddress>) deliveryAddressRepository.findAll();
    }

    public DeliveryAddress getById(Long id) {
        return deliveryAddressRepository.findById(id).get();
    }

    public void delete(Long id) {
        deliveryAddressRepository.deleteById(id);
    }
}
