package utn_dany.Guia2SpringWeb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn_dany.Guia2SpringWeb.model.ProductEntity;
import utn_dany.Guia2SpringWeb.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
    }

    public ProductEntity create(ProductEntity product) {
        return repository.save(product);
    }

    public ProductEntity update(Long id, ProductEntity product) {

        ProductEntity existing = getById(id);

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return repository.update(existing);
    }

    public void delete(Long id) {

        ProductEntity existing = getById(id);

        if (!repository.delete(existing)) {
            throw new RuntimeException("No se pudo eliminar");
        }
    }
}
