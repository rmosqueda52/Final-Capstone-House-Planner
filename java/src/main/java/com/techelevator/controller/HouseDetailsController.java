package com.techelevator.controller;

import com.techelevator.dao.HouseDetailsDao;
import com.techelevator.model.HouseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/gethousedetails/{id}")
    public HouseDetails getHouseDetails (@PathVariable Long id){
        return houseDetailsDao.getHouseDetails(id);
    }


    @GetMapping(value = "/getallhouses/{id}")
    public List<HouseDetails> getAllHouses (@PathVariable Long id){
        return houseDetailsDao.getAllHouses(id);
    }






}
