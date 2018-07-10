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
public class ItemDao {

    private int currUser;
    // Injected database connection:

    @PersistenceContext
    private EntityManager em;

    // Stores a new guest:
    @Transactional
    public void persist(Item item) {
        em.persist(item);
    }

    @Transactional
    public void delete(int id) {
        Item i = getItem(id);
        em.remove(i);
    }

    public Item getItem(int id) {
        TypedQuery<Item> query = em.createQuery(
                "SELECT i FROM Item i WHERE i.id = " + id, Item.class);
        return query.getSingleResult();
    }

    public List<Item> getAllItems() {
        TypedQuery<Item> query = em.createQuery(
                "SELECT i FROM Item i ORDER BY i.id", Item.class);
        return query.getResultList();
    }

    public List<Item> getAllItemsOfUser(int id) {
        //Item user = getUser(id);
        TypedQuery<Item> query = em.createQuery(
                "SELECT i FROM Item i where i.author.id=" + id, Item.class);
        return query.getResultList();
    }

    public List<Item> getAllItemsOfCurrUser() {
        int id = currUser;
        if (id < 0) {
            return getAllItems();
        }
        //Item user = getUser(id);
        TypedQuery<Item> query = em.createQuery(
                "SELECT i FROM Item i where i.author.id=" + id, Item.class);
        return query.getResultList();
    }

    public int getCurrUser() {
        return currUser;
    }

    public void setCurrUser(int currUser) {
        this.currUser = currUser;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    void Check(int parseInt) {
        TypedQuery<Item> query = em.createQuery(
                "UPDATE Item i Set i.checked=true where i.id=" + parseInt, Item.class);
        query.executeUpdate();
    }

}
