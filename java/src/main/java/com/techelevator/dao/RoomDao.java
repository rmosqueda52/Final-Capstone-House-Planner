package com.techelevator.dao;

import com.techelevator.model.Room;

public interface RoomDao {

    boolean addRoomToFloor(Room room, int floorId);

    boolean changeRoomSize(Room room);

    boolean deleteRoom(int roomId);

    boolean changeRoomName(Room room);


}
