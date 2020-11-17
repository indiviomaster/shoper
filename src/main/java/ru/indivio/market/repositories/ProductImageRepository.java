package ru.indivio.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.indivio.market.entites.ProductImage;
import ru.indivio.market.entites.Role;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
	ProductImage findOneByProductId(Long id);
}
