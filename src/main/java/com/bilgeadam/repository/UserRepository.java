package com.bilgeadam.repository;

import com.bilgeadam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findOptionalByEmail(String email);

    Optional<User> findOptionalByEmailAndPassword(String email, String password);

    Boolean   existsByEmailAndPassword(String email, String password);
    Boolean  existsByEmail(String email);

    //Kullanıcıları isme göre sıralı getiriniz
    List<User> findAllByOrderByName();

    //bir isimdeki kullanıcıları getiren metot
    List<User> findAllByName(String name);

    List<User> findAllByNameContainsIgnoreCase(String value);
    List<User> findAllByEmailContainsIgnoreCase(String value);

}
