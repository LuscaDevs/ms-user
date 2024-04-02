package br.com.meusprocessos.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meusprocessos.user.model.User;
import br.com.meusprocessos.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findUserById(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado"));
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        // Verifica se o usuário existe antes de excluir
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado");
        }
    }

    public User editUserPassword(Long id, String password) {
        User editedUser = findUserById(id);
        editedUser.setPassword(password);
        return createUser(editedUser);
    }
}
