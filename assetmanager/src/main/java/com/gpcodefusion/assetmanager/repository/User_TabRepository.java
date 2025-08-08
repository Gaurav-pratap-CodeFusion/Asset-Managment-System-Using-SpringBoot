package com.gpcodefusion.assetmanager.repository;

import com.gpcodefusion.assetmanager.model.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_TabRepository extends JpaRepository<UsersDetails, String> {

}

