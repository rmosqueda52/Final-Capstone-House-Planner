package com.techelevator.controller;

import com.techelevator.dao.RoomDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @PostMapping(value ="/add-room/{id}") // id = floorId
    public boolean addRoomToFloor(@RequestBody Room room, @PathVariable int id){
        return roomDao.addRoomToFloor(room, id);
    }

    @PutMapping(value = "/change-room-size/{id}") // id = room_id
    public boolean changeRoomSize(@RequestBody Room room, @PathVariable int id){
        return roomDao.changeRoomSize(room, id);
    }





}
