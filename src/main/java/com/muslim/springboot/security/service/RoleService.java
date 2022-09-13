package com.muslim.springboot.security.service;

import com.muslim.springboot.security.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRoles();

    Role getRoleByName(String name);
}
