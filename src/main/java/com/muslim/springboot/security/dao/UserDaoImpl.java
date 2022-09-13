package com.muslim.springboot.security.dao;

import org.springframework.stereotype.Repository;
import com.muslim.springboot.security.model.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT user FROM User user", User.class)
                .getResultList();
    }

    @Override
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) entityManager.createQuery("SELECT user FROM User user " +
                        "JOIN FETCH user.roles WHERE user.username = :username")
                .setParameter("username", username).getSingleResult();
    }
}
