package com.techelevator.dao;

import com.techelevator.model.HouseDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class JdbcHouseDetailsDao implements HouseDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcHouseDetailsDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean createHouse(HouseDetails houseDetails) {
        String sql = "INSERT INTO house_details (house_name, foundation_size, region) " +
                        "VALUES (?,?,?)";

        return jdbcTemplate.update(sql,houseDetails.getHouseName(),houseDetails.getFoundationSize(),houseDetails.getRegion()) ==1;
    }

    @Override
    public String getHouseDetails(Long houseId) {
        return null;
    }

    @Override
    public List<HouseDetails> getAllHouses(Long userId) {
        return null;
    }
}
