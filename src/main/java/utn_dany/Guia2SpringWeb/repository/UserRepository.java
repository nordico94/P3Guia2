package utn_dany.Guia2SpringWeb.repository;

import org.springframework.stereotype.Repository;
import utn_dany.Guia2SpringWeb.model.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class UserRepository implements IRepository<UserEntity> {

    private final List<UserEntity> users = new ArrayList<>();

    public UserRepository() {
        users.add(new UserEntity(1L, "Juan Perez", "juan@mail.com"));
        users.add(new UserEntity(2L, "Maria Lopez", "maria@mail.com"));
        users.add(new UserEntity(3L, "Carlos Gomez", "carlos@mail.com"));
    }

    @Override
    public List<UserEntity> findAll() {
        return users;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        users.add(entity);
        return entity;
    }

    @Override
    public boolean delete(UserEntity entity) {
        return users.remove(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return users.stream()
                .filter(u -> u.getId().equals(entity.getId()))
                .findFirst()
                .map(existing -> {
                    existing.setName(entity.getName());
                    existing.setEmail(entity.getEmail());
                    return existing;
                }).orElseThrow(()->new NoSuchElementException("Entity not found"));
    }

    public Optional<UserEntity> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
