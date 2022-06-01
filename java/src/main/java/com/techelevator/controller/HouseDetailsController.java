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

    @GetMapping(value = "/get-house-details/{id}")
    public HouseDetails getHouseDetails (@PathVariable Long id){
        return houseDetailsDao.getHouseDetails(id);
    }


    @GetMapping(value = "/get-all-houses/{id}")
    public List<HouseDetails> getAllHouses (@PathVariable Long id){
        return houseDetailsDao.getAllHousesByUserId(id);
    }

    @PutMapping(value = "/update-floors/{id}")
    public boolean addFloors(@PathVariable Long id, @RequestBody HouseDetails houseDetails){
        return houseDetailsDao.addFloors(houseDetails, id);
    }

    @PutMapping(value = "/update-subtract-floors/{id}")
    public boolean removeFloors(@PathVariable Long id, @RequestBody HouseDetails houseDetails){
        return houseDetailsDao.removeFloors(houseDetails, id);
    }

    @DeleteMapping(value = "/delete-house/{id}")
    public boolean deleteHouse(@PathVariable Long id){
        return  houseDetailsDao.deleteHouse(id);
    }

    @PreAuthorize("permitAll")
    @GetMapping(value = "/guest")
    public List<HouseDetails> getAllHousesForGuestUser(){
        return houseDetailsDao.getAllHousesForGuestUser();
    }




}
