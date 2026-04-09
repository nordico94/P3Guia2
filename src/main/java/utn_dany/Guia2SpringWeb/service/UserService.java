package utn_dany.Guia2SpringWeb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn_dany.Guia2SpringWeb.model.UserEntity;
import utn_dany.Guia2SpringWeb.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    public UserEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }

    public UserEntity create(UserEntity user) {
        return repository.save(user);
    }

    public UserEntity update(Long id, UserEntity user) {

        UserEntity existing = getById(id);

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repository.update(existing);
    }

    public void delete(Long id) {

        UserEntity existing = getById(id);

        if (!repository.delete(existing)) {
            throw new RuntimeException("No se pudo eliminar");
        }
    }
}
