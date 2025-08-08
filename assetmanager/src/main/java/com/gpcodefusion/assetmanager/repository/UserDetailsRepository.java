package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UsersDetails, String> {
    Optional<UsersDetails> findByEmail(String email);
    Optional<UsersDetails> findByPasswordResetToken(String PasswordResetToken);
}
