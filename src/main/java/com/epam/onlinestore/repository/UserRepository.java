package com.epam.onlinestore.repository;

import com.epam.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    User getByLogin(String login);

    @Query("select us from User us where us.email=?1")
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("delete from User us where us.email=?1")
    void deleteUser(String email);
}
