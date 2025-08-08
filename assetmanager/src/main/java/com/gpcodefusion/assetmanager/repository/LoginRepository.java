package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUserIdAndUserPassword(String userId, String userPassword);
}
