package com.simbirsoft.modeles;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.getNotShowApproveByUser", query = "SELECT c FROM Book c WHERE c.toShow=false AND c.approve=true AND c.userName=:userName"),
        @NamedQuery(name = "Book.getNotApprove", query = "SELECT c FROM Book c WHERE c.approve=false"),
        @NamedQuery(name = "Book.getApprove", query = "SELECT c FROM Book c WHERE c.approve=true"),
        @NamedQuery(name = "Book.getApproveForShow", query = "SELECT c FROM Book c WHERE c.approve=true AND c.toShow=true"),
        @NamedQuery(name = "Book.getWithISBN", query = "SELECT c FROM Book c WHERE c.ISBN=:ISBN"),
})
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String author;
    private String title;
    private Integer year;
    private String publishingHouse;
    private String ISBN;
    private Integer pagesCount;
    private String edition;
    private String language;
    private String userName;
    private Boolean toShow;
    private Boolean approve;
    private String imagePath;
    private String bookPath;

    public Book() {
    }

    public Book(String author, String title, Integer year, String publishingHouse, String ISBN, Integer pagesCount, String edition, String language, String imagePath, String bookPath, String userName) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.publishingHouse = publishingHouse;
        this.ISBN = ISBN;
        this.pagesCount = pagesCount;
        this.edition = edition;
        this.language = language;
        this.imagePath = imagePath;
        this.bookPath = bookPath;
        this.userName = userName;
        this.approve = false;
        this.toShow = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getToShow() {
        return toShow;
    }

    public void setToShow(Boolean toShow) {
        this.toShow = toShow;
    }
}
