package com.muslim.springboot.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.muslim.springboot.security.model.Role;
import com.muslim.springboot.security.model.User;
import com.muslim.springboot.security.service.RoleService;
import com.muslim.springboot.security.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showAdmin(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> userList = userService.listUsers();
        model.addAttribute("allUsers", userList);
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.listRoles());
        return "user-details";
    }

    @PutMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        User u = userService.getUser(user.getId());
        for (Role r : user.getRoles()) {
            roles.add(roleService.getRoleByName(r.getName()));
        }
        user.setRoles(roles);
        if (u != null && u.getPassword().equals(user.getPassword())) {
            u.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam("userID") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.listRoles());
        return "user-details";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("userID") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
