package com.techelevator.controller;

import com.techelevator.dao.HouseDetailsDao;
import com.techelevator.model.HouseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class HouseDetailsController {
    @Autowired
    private HouseDetailsDao houseDetailsDao;

    @PostMapping(value = "/create")
    public boolean createHouse (@RequestBody HouseDetails houseDetails){
        System.out.println(houseDetails);
        return houseDetailsDao.createHouse(houseDetails);
    }






}
