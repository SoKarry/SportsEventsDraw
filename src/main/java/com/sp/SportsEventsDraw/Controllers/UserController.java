package com.sp.SportsEventsDraw.Controllers;

import com.sp.SportsEventsDraw.Repositories.UserRepo;
import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Role;
import com.sp.SportsEventsDraw.domain.Type;
import com.sp.SportsEventsDraw.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(@RequestParam(required = false) String role,
                           @RequestParam(required = false, defaultValue = "") String filter,
                           Model model) {
        List<User> users = userRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            if (role != null && !role.isEmpty()) {
                model.addAttribute("role", role);
                users = userRepo.findUsersByRolesAndUsernameContaining(Role.valueOf(role), filter);
            } else {
                users = userRepo.findUsersByUsernameContaining(filter);
            }
        }
        else {
            if (role != null) {
                model.addAttribute("role", role);
                users = userRepo.findUsersByRoles(Role.valueOf(role));
            }
        }
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", users);
        model.addAttribute("filter", filter);
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @GetMapping("/del/{user}")
    public String delUser(@PathVariable User user, Model model) {
        if (user.getId()!=1){
            userRepo.deleteById(user.getId());
        }
        else {
            model.addAttribute("error", "Нельзя удалить Главного Администратора!");
        }
        return "redirect:/user";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}