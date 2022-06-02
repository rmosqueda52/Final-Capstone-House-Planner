package com.techelevator.dao;

import com.techelevator.model.HouseDetails;

import java.util.List;

public interface HouseDetailsDao {

    boolean createHouse(HouseDetails houseDetails);

    HouseDetails getHouseDetails (Long houseId);

    List <HouseDetails> getAllHousesByUserId (Long userId);

    boolean addFloors (HouseDetails houseDetails, Long houseId);

    boolean removeFloors (HouseDetails houseDetails, Long houseId);

    boolean deleteHouse(Long houseId);

    List<HouseDetails> getAllHousesForGuestUser();

    boolean addFloorToDatabase(int houseId, int floorLevel);

}
