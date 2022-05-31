package com.techelevator.dao;

import com.techelevator.model.HouseDetails;

import java.util.List;

public interface HouseDetailsDao {

    boolean createHouse(HouseDetails houseDetails);

    String getHouseDetails (Long houseId);

    List <HouseDetails> getAllHouses (Long userId);




}