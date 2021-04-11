package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}