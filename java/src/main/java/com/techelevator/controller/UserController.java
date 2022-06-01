package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {
    @Autowired
    private UserDao userDao;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @DeleteMapping(value = "/delete-user/{Id}") // user id
    public boolean deleteUser (@PathVariable Long Id) {
        return userDao.deleteUser(Id);
    }



}
