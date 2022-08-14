package com.spring.springSecurity.repository;

import com.spring.springSecurity.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Long> {

    UserDetails findByUserName(String userName);
}
