package com.simbirsoft.controllers;

import com.simbirsoft.modeles.User;
import com.simbirsoft.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;


@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private static final Integer MIN_LENGTH = 3;

    public UserBean() {
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void add(String login, String password, String role) {
        if (login.length() > MIN_LENGTH && password.length() > MIN_LENGTH && RoleBean.getRoleList().contains(role)) {
            try {
                if (userService.get(login).isEmpty()) {
                    User user = new User(login, password, role);
                    userService.update(user);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Пользователь с таким именем уже существует."));
                }
            } catch (NoResultException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Не удалось добавить пользователя в базу данных."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Введенные данные не соответствуют требованиям."));
        }
    }

    public void remove(int id) {
        try {
            userService.delete(id);
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Пользователь не найден."));
        }
    }

    public void update(int id, String password, String role) {
        try {
            User user = userService.get(id);
            if (user != null) {
                if (password.length() >= MIN_LENGTH) {
                    user.setPassword(password);
                }
                if (RoleBean.getRoleList().contains(role)) {
                    user.setRole(role);
                }
                userService.update(user);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Предупреждение", "Пользователь не найден."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Проблемы при работе с базой данных."));
        }
    }
}
