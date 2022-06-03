package com.techelevator.controller;

import com.techelevator.dao.HouseDetailsDao;
import com.techelevator.model.Floor;
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
    public Long createHouse (@RequestBody HouseDetails houseDetails){
        return houseDetailsDao.createHouse(houseDetails);
    }

    @GetMapping(value = "/get-house-details/{id}")//house ID
    public HouseDetails getHouseDetails (@PathVariable Long id){
        return houseDetailsDao.getHouseDetails(id);
    }


    @GetMapping(value = "/get-all-houses/{id}")//user ID
    public List<HouseDetails> getAllHouses (@PathVariable Long id){
        return houseDetailsDao.getAllHousesByUserId(id);
    }

    @PutMapping(value = "/update-floors/{houseId}")
    public boolean addFloorsToExistingFloors(@PathVariable Long houseId){
        return houseDetailsDao.addFloorToExistingFloors(houseId);
    }

    @PutMapping(value = "/update-subtract-floors/{id}") // Validation to be done on the Front end, check for floors only
    // in the house that they are looking at.
    public boolean removeFloors(@PathVariable int id, @RequestBody HouseDetails houseDetails){
        return houseDetailsDao.removeFloorsFromHouseTable(houseDetails, id );
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


    @GetMapping(value = "/get-all-floors/{id}")
    public List<Floor> getAllFloorsByHouseId (@PathVariable Long id){
        return houseDetailsDao.getAllFloorsByHouseId(id);
    }

}
