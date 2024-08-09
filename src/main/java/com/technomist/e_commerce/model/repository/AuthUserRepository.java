package com.technomist.e_commerce.model.repository;

import com.technomist.e_commerce.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findAuthUserByUsernameIgnoreCase(String username);

    Optional<AuthUser> findAuthUserByEmailIgnoreCase(String email);

}
