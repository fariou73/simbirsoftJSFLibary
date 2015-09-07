package com.simbirsoft.services;

import com.simbirsoft.modeles.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserService {
    private static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("PostgresLibaryUl").createEntityManager();

    public void update(User user){
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.merge(user);
        ENTITY_MANAGER.getTransaction().commit();
    }

    public List<User> getAll() {
        TypedQuery<User> namedQuery = ENTITY_MANAGER.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }

    public void delete(int id){
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.remove(get(id));
        ENTITY_MANAGER.getTransaction().commit();
    }

    public User get(int id){
        return ENTITY_MANAGER.find(User.class, id);
    }

    public List<User> get(String login){
        TypedQuery<User> namedQuery = ENTITY_MANAGER.createNamedQuery("User.findByName", User.class).setParameter("login", login);
        return namedQuery.getResultList();
    }

    public void clear(){
        ENTITY_MANAGER.getTransaction().begin();
        Query query = ENTITY_MANAGER.createQuery("DELETE FROM User c WHERE c.id=c.id");
        query.executeUpdate();
        ENTITY_MANAGER.getTransaction().commit();
    }

    public User get(String login, String password){
        TypedQuery<User> namedQuery = ENTITY_MANAGER.createNamedQuery("User.authorizeSearch", User.class).setParameter("login", login).setParameter("password", password);
        return namedQuery.getSingleResult();
    }
}
