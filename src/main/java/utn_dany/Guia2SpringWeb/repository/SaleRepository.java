package utn_dany.Guia2SpringWeb.repository;


import org.springframework.stereotype.Repository;
import utn_dany.Guia2SpringWeb.model.SaleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class SaleRepository implements IRepository<SaleEntity> {

    private final List<SaleEntity> sales = new ArrayList<>();

    @Override
    public List<SaleEntity> findAll() {
        return sales;
    }

    @Override
    public SaleEntity save(SaleEntity entity) {
        sales.add(entity);
        return entity;
    }

    @Override
    public boolean delete(SaleEntity entity) {
        return sales.remove(entity);
    }

    @Override
    public SaleEntity update(SaleEntity entity) {
        return sales.stream()
                .filter(s -> s.getId().equals(entity.getId()))
                .findFirst()
                .map(existing -> {
                    existing.setQuantity(entity.getQuantity());
                    existing.setTotalPrice(entity.getTotalPrice());
                    return existing;
                }).orElseThrow(()->new NoSuchElementException("Entity not found"));
    }

    public Optional<SaleEntity> findById(Long id) {
        return sales.stream().filter(s -> s.getId().equals(id)).findFirst();
    }
}
