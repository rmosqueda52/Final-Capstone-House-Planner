package com.techelevator.dao;

import com.techelevator.model.Room;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcRoomDao implements RoomDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRoomDao (JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public boolean addRoomToFloor(Room room, int floorId) {
        String sql ="INSERT INTO room_details (room_name, room_size, floor_id) VALUES(?,?,?)";
        return jdbcTemplate.update(sql, room.getRoomName(), room.getRoomSize(), floorId) ==1 ;
    }

    @Override
    public boolean changeRoomSize(Room room) {
        return false;
    }

    @Override
    public boolean deleteRoom(int roomId) {
        return false;
    }

    @Override
    public boolean changeRoomName(Room room) {
        return false;
    }
}
