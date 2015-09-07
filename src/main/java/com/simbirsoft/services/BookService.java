package com.simbirsoft.services;

import com.simbirsoft.modeles.Book;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookService {
    private static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("PostgresLibaryUl").createEntityManager();

    public Book get(int id) {
        return ENTITY_MANAGER.find(Book.class, id);
    }

    public List<Book> getWithISBN(String ISBN) {
         TypedQuery<Book> namedQuery = ENTITY_MANAGER.createNamedQuery("Book.getWithISBN", Book.class).setParameter("ISBN", ISBN);
        return namedQuery.getResultList();
    }

    public List<Book> getApprove() {
        TypedQuery<Book> namedQuery = ENTITY_MANAGER.createNamedQuery("Book.getApprove", Book.class);
        return namedQuery.getResultList();
    }

    public List<Book> getNotApprove() {
        TypedQuery<Book> namedQuery = ENTITY_MANAGER.createNamedQuery("Book.getNotApprove", Book.class);
        return namedQuery.getResultList();
    }

    public List<Book> getNotShowApproveByUser(String userName) {
        TypedQuery<Book> namedQuery = ENTITY_MANAGER.createNamedQuery("Book.getNotShowApproveByUser", Book.class).setParameter("userName", userName);
        return namedQuery.getResultList();
    }

    public List<Book> getApproveForShow() {
        TypedQuery<Book> namedQuery = ENTITY_MANAGER.createNamedQuery("Book.getApproveForShow", Book.class);
        return namedQuery.getResultList();
    }

    public void remove(int id) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.remove(get(id));
        ENTITY_MANAGER.getTransaction().commit();
    }

    public void update(Book book) {
        ENTITY_MANAGER.getTransaction().begin();
        ENTITY_MANAGER.merge(book);
        ENTITY_MANAGER.getTransaction().commit();
    }

}
