package com.simbirsoft.controllers;

import com.simbirsoft.modeles.Book;
import com.simbirsoft.modeles.PrimeFacesFile;
import com.simbirsoft.services.BookService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named(value = "bookBean")
@RequestScoped
public class BookBean implements Serializable {


    @Inject
    private BookService bookService;

    private static final String PATH_FROM_SAVE_FILE = "D:\\example\\";
    private static final Integer MIN_AUTHOR_LENGTH = 5;
    private static final Integer MIN_TITLE_LENGTH = 1;
    private static final Integer MIN_YEAR = 1000;
    private static final Integer MAX_YEAR = 2100;
    private static final Integer MIN_PUBLISHING_LENGTH = 1;
    private static final Integer MIN_ISBN_LENGTH = 10;
    private static final Integer MIN_PAGES_COUNT = 1;
    private static final Integer MIN_EDITION_LENGTH = 1;
    private static final Integer MIN_LANGUAGE_LENGTH = 1;
    private static final String VALID_BOOK_EXTENSTION = ".djvu";
    private static final String VALID_IMAGE_EXTENSTION = ".jpg";
    private PrimeFacesFile imageBook = new PrimeFacesFile();
    private PrimeFacesFile bookFile = new PrimeFacesFile();

    public BookBean() {
    }


    public PrimeFacesFile getImageBook() {
        return imageBook;
    }

    public void setImageBook(PrimeFacesFile imageBook) {
        this.imageBook = imageBook;
    }

    public PrimeFacesFile getBookFile() {
        return bookFile;
    }

    public void setBookFile(PrimeFacesFile bookFile) {
        this.bookFile = bookFile;
    }

    public List<Book> getWithISBN(String ISBN) {
        return bookService.getWithISBN(ISBN);
    }

    public List<Book> getApprove() {
        return bookService.getApprove();
    }

    public List<Book> getApproveForShow() {
        return bookService.getApproveForShow();
    }

    public List<Book> getNotApprove() {
        return bookService.getNotApprove();
    }

    public List<Book> getNotApproveByUser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return bookService.getNotShowApproveByUser(request.getRemoteUser());
    }

    public void add(String author, String title, Integer year, String publishingHouse, String ISBN, Integer pagesCount, String edition, String language, String userName) {
        Boolean argumentsCorrect = false;
        Boolean fileLoaded = false;

        try {
            argumentsCorrect = author.length() >= MIN_AUTHOR_LENGTH && title.length() >= MIN_TITLE_LENGTH && year >= MIN_YEAR && year <= MAX_YEAR
                    && publishingHouse.length() >= MIN_PUBLISHING_LENGTH && ISBN.length() >= MIN_ISBN_LENGTH && pagesCount >= MIN_PAGES_COUNT
                    && edition.length() >= MIN_EDITION_LENGTH && language.length() >= MIN_LANGUAGE_LENGTH;
            fileLoaded = bookFile.getUploadedFile() != null && imageBook.getUploadedFile() != null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "При проверке данных возникла ошибка. Проверьте соответствие типов данных."));
        }

        if (argumentsCorrect && fileLoaded) {
            if (bookService.getWithISBN(ISBN).isEmpty()) {
                try {
                    String imageBookExtension = imageBook.getUploadedFile().getFileName().substring(imageBook.getUploadedFile().getFileName().lastIndexOf("."));
                    String bookExtension = bookFile.getUploadedFile().getFileName().substring(bookFile.getUploadedFile().getFileName().lastIndexOf("."));
                    Boolean extensionValid = VALID_BOOK_EXTENSTION.equals(bookExtension) && VALID_IMAGE_EXTENSTION.equals(imageBookExtension);
                    if (extensionValid) {
                        String pathFromImage = PATH_FROM_SAVE_FILE + ISBN + imageBookExtension;
                        String pathFromBook = PATH_FROM_SAVE_FILE + ISBN + bookExtension;
                        imageBook.transferFile(pathFromImage);
                        bookFile.transferFile(pathFromBook);
                        Book book = new Book(author, title, year, publishingHouse, ISBN, pagesCount, edition, language, ISBN + imageBookExtension, ISBN + bookExtension, userName);
                        bookService.update(book);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Успех", "Ваша заявка успешно добавлена."));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Вы пытаетесь загрузить файл с недопустимым расширением."));
                    }
                } catch (IOException e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Не удалось сохранить файл на сервер."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Книга с таким ISBN уже загружена."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Внимание", "Одно или несколько полей были заполнены неправильно."));
        }
    }

    public void remove(int id) {
        bookService.remove(id);
    }

    public void rePost(int id) {
        try {
            Book book = bookService.get(id);
            book.setApprove(false);
            bookService.update(book);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Ошибка при работе с базой данных."));
        }
    }

    public void approved(int id) {
        try {
            Book book = bookService.get(id);
            book.setApprove(true);
            book.setToShow(true);
            bookService.update(book);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Ошибка при работе с базой данных."));
        }
    }

    public void notApproved(int id) {
        try {
            Book book = bookService.get(id);
            book.setApprove(true);
            bookService.update(book);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Ошибка при работе с базой данных."));
        }
    }

}