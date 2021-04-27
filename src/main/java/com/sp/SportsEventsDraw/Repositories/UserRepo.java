package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Role;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findUsersByRolesAndUsernameContaining(Role role, String username);
    List<User> findUsersByUsernameContaining(String username);
    List<User> findUsersByRoles(Role role);
}