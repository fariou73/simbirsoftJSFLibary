package com.simbirsoft.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named(value = "roleBean")
@RequestScoped
public class RoleBean {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private String currentRole;



    private static final List<String> ROLE_LIST = Arrays.asList(ADMIN, USER);


    public RoleBean() {
    }

    public static List<String> getRoleList() {
        return ROLE_LIST;
    }

    public String getAdminRole() {
        return ADMIN;
    }

    public String getUserRole() {
        return USER;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }
}
