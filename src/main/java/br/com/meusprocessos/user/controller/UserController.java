package br.com.meusprocessos.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.meusprocessos.user.model.User;
import br.com.meusprocessos.user.model.UserFieldUpdateRequest;
import br.com.meusprocessos.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            if (service.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sem usuários");
            } else {
                return ResponseEntity.ok().body(service.findAll());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao buscar usuários: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@RequestParam Long id) {
        try {
            User user = service.findUserById(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao buscar usuário Id: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(service.createUser(user));
    }

    @PutMapping("/{id}/{field}")
    public ResponseEntity<Object> editUserFields(@PathVariable Long id, @PathVariable String field,
            @RequestBody UserFieldUpdateRequest newValue) {
        return ResponseEntity.ok().body(service.editUserField(id, field, newValue));
    }

}
