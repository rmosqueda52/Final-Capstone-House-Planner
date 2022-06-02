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
    public boolean changeRoomSize(Room room, int roomId) {
        String sql = "UPDATE room_details SET room_size = ? WHERE room_id = ?";
        return jdbcTemplate.update(sql,room.getRoomSize(), roomId) == 1;
    }

    @Override
    public boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM room_details WHERE room_id = ?";
        return jdbcTemplate.update(sql,roomId) == 1;
    }

    @Override
    public boolean changeRoomName(Room room, int roomId) {
        String sql = "UPDATE room_details SET room_name = ? WHERE room_id = ?";
        return jdbcTemplate.update(sql,room.getRoomName(), room.getRoomId()) == 1;
    }

    @Override
    public boolean changeRoomFloor(Room room, int floorId) {
        String sql = "UPDATE room_details SET floor_id = ? WHERE room_id =?";
        return jdbcTemplate.update(sql,floorId, room.getRoomId()) == 1;
    }
}
