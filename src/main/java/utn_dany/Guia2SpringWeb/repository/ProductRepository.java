package utn_dany.Guia2SpringWeb.repository;

import org.springframework.stereotype.Repository;
import utn_dany.Guia2SpringWeb.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ProductRepository implements IRepository<ProductEntity> {

    private final List<ProductEntity> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new ProductEntity(1L, "Notebook", 1200, 10));
        products.add(new ProductEntity(2L, "Mouse", 25, 50));
        products.add(new ProductEntity(3L, "Teclado", 45, 30));
    }

    @Override
    public List<ProductEntity> findAll() {
        return products;
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        products.add(entity);
        return entity;
    }

    @Override
    public boolean delete(ProductEntity entity) {
        return products.remove(entity);
    }

    @Override
    public ProductEntity update(ProductEntity entity) {
        return products.stream()
                .filter(p -> p.getId().equals(entity.getId()))
                .findFirst()
                .map(existing -> {
                    existing.setName(entity.getName());
                    existing.setPrice(entity.getPrice());
                    existing.setStock(entity.getStock());
                    return existing;
                }).orElseThrow(()->new NoSuchElementException("Entity not found"));
    }

    public Optional<ProductEntity> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
