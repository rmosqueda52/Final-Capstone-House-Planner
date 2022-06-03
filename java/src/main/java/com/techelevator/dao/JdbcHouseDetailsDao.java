package com.techelevator.dao;

import com.techelevator.model.Floor;
import com.techelevator.model.HouseDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHouseDetailsDao implements HouseDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcHouseDetailsDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean createHouse(HouseDetails houseDetails) {
        String sql = "INSERT INTO house_details (house_name, foundation_size, region, user_id, is_private,number_of_floors) " +
                        "VALUES (?,?,?,?,?,?) RETURNING house_id";
        Long house_id = jdbcTemplate.queryForObject(sql,Long.class, houseDetails.getHouseName(),houseDetails.getFoundationSize(),
                houseDetails.getRegion(), houseDetails.getUserId(), houseDetails.isPrivate(), houseDetails.getNumberOfFloors());
        return addFloorsWhenHouseisCreated(houseDetails, house_id);
    }



    @Override
    public HouseDetails getHouseDetails(Long houseId) {
        HouseDetails houseDetails = new HouseDetails();
        String sql = "SELECT * FROM house_details WHERE house_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, houseId);
        while(results.next()) {
            houseDetails = mapRowToHouseDetails(results);
        }
        return houseDetails;
    }

    @Override
    public List<HouseDetails> getAllHousesByUserId(Long userId) {
        List<HouseDetails> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_details WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            HouseDetails houseDetails = mapRowToHouseDetails(results);
            houses.add(houseDetails);
        }
        return houses;
    }

//    @Override
//    public boolean addFloors(HouseDetails houseDetails, Long houseId) {
//        String sql = "UPDATE house_details SET number_of_floors = number_of_floors + ? WHERE house_id =?";
//        return jdbcTemplate.update(sql, houseDetails.getNumberOfFloors(), houseId)==1;
//    }

    @Override
    public boolean addFloorsWhenHouseisCreated(HouseDetails houseDetails, Long houseId) {
        String sql = "INSERT INTO floor (house_id, floor_level) VALUES (?, ?)";

        for(int i=1;i<=houseDetails.getNumberOfFloors();i++){
            if(i== houseDetails.getNumberOfFloors()){
                return jdbcTemplate.update(sql,houseId,i) ==1;
            }
            jdbcTemplate.update(sql,houseId,i);
        }


        return false;
    }

    @Override
    public boolean removeFloorsFromHouseTable(HouseDetails houseDetails, int floorId) {
        String sql = "UPDATE house_details SET number_of_floors = number_of_floors - 1 WHERE house_id =?";
        jdbcTemplate.update(sql, houseDetails.getHouseId());
        return removeFloorsFromFloorTable(floorId);
    }

    @Override
    public boolean removeFloorsFromFloorTable(int floorId) {
        String sql = "DELETE FROM room_details WHERE floor_id =?";
        jdbcTemplate.update(sql,floorId);
        String sql2 ="DELETE FROM floor WHERE floor_id = ?";
        return jdbcTemplate.update(sql2, floorId)==1;
    }

    @Override
    public boolean addFloorToExistingFloors(Long houseId) {
        String sql = "UPDATE house_details SET number_of_floors = number_of_floors + 1 WHERE house_id =?";
        jdbcTemplate.update(sql,houseId);
        return addFloorToFloorTable(houseId);
    }

    @Override
    public boolean addFloorToFloorTable(Long houseId) {
        String sql = "INSERT INTO floor (floor_level, house_id) VALUES ((SELECT COUNT(floor_level) + 1 FROM floor WHERE house_id = ?),?)";
        return jdbcTemplate.update(sql,houseId,houseId) == 1;
    }

    @Override
    public List<Floor> getAllFloorsByHouseId(Long houseId) {
        List<Floor> floors = new ArrayList<>();
        String sql ="SELECT * FROM floor WHERE house_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,houseId);

        while(results.next()) {
            floors.add(mapRowToFloor(results));
        }
        return floors;
    }


    @Override
    public boolean deleteHouse(Long houseId) {
        String sql = "DELETE FROM house_details WHERE house_id = ?";
        return jdbcTemplate.update(sql,houseId) == 1;
    }

    @Override
    public List<HouseDetails> getAllHousesForGuestUser() {
        List<HouseDetails> houses = new ArrayList<>();
        String sql = "SELECT * FROM house_details WHERE is_private = false";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            HouseDetails houseDetails = mapRowToHouseDetails(results);
            houses.add(houseDetails);
        }
        return houses;
    }


    private HouseDetails mapRowToHouseDetails (SqlRowSet rs) {
        HouseDetails houseDetails = new HouseDetails();
        houseDetails.setFoundationSize(rs.getInt("foundation_size"));
        houseDetails.setHouseId(rs.getLong("house_id"));
        houseDetails.setHouseName(rs.getString("house_name"));
        houseDetails.setRegion(rs.getString("region"));
        houseDetails.setUserId(rs.getLong("user_id"));
        return houseDetails;
    }

    private Floor mapRowToFloor(SqlRowSet results){
        Floor floor = new Floor();
        floor.setFloorId(results.getInt("floor_id"));
        floor.setFloorLevel(results.getInt("floor_level"));
        floor.setHouseId(results.getInt("house_id"));
        return floor;
    }



}
