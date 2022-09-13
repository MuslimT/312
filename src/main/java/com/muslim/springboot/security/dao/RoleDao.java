package com.muslim.springboot.security.dao;

import com.muslim.springboot.security.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> listRoles();

    Role getRoleByName(String name);
}
