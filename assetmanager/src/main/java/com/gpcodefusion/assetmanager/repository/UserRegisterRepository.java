package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegisterRepository extends JpaRepository<UsersDetails, String> {
    boolean existsByEmail(String email);
}
