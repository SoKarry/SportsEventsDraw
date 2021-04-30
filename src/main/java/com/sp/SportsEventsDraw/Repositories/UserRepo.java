package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Role;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

//Интерфейс для пользователей
public interface UserRepo extends JpaRepository<User, Long> {
    //Поиск пользователя по имени
    User findByUsername(String username);
    //Поиск пользователей по роли и имени
    List<User> findUsersByRolesAndUsernameContaining(Role role, String username);
    //Поиск пользователей по части имени
    List<User> findUsersByUsernameContaining(String username);
    //Поиск пользователей по роли
    List<User> findUsersByRoles(Role role);
    //Удаление пользователя
    @Override
    void deleteById(@NonNull Long id);
}