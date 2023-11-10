package com.example.security.repository;

import com.example.security.entity.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Long> {

    /*static SecurityEntity findByUsername(String username) {
        //return findByUsername(username);
        return null;
    }*/

    SecurityEntity findByUsername(String username);
}
