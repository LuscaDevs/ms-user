package br.com.meusprocessos.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.meusprocessos.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
