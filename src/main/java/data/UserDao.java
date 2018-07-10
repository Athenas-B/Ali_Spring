/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author olda9
 */
@Component
public class UserDao {

    // Injected database connection:
    @PersistenceContext
    private EntityManager em;

    // Stores a new guest:
    @Transactional
    public void persist(User user) {
        em.persist(user);
    }

    @Transactional
    public void delete(int id) {
        User i = getUser(id);
        em.remove(i);
    }
    // Retrieves all the guests:
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u ORDER BY u.id", User.class);
        return query.getResultList();
    }

    public User getUser(String login) {
        login = "%" + login + "%";
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.login LIKE :text", User.class);
        query.setParameter("text", login);
        return query.getSingleResult();
    }

    public User getUser(int id) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.id = " + id, User.class);
        System.out.println(id);
        return query.getSingleResult();
    }

    public boolean exists(String login) {
        login = "%" + login + "%";
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.login LIKE :text", User.class);
        query.setParameter("text", login);
        List<User> resultList = query.getResultList();
        return !resultList.isEmpty();
    }
}
