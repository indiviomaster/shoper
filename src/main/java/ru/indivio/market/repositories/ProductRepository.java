package ru.indivio.market.repositories;

import ru.indivio.market.entites.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findOneByTitle(String title);
    List<Product> findAll();
    @Query(value ="select id, category_id, short_description, " +
            "create_at, full_description, title, price, vendor_code, " +
            "title from products where id = ?1", nativeQuery = true)
    Product myQuery(Long id);
}
