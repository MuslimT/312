package com.muslim.springboot.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.muslim.springboot.security.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Role> listRoles() {
        String jpql = "SELECT r FROM Role r";
        TypedQuery<Role> query = entityManager.createQuery(jpql, Role.class);
        return query.getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager
                .createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name);
        return query.getSingleResult();
    }
}
