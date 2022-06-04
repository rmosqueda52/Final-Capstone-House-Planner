package com.techelevator.dao;

import com.techelevator.model.Room;

public interface RoomDao {

    boolean addRoomToFloor(Room room, int floorId);

    boolean changeRoomSize(Room room, int roomId);

    boolean deleteRoom(int roomId);

    boolean changeRoomName(Room room, int roomId);

    boolean changeRoomFloor (Room room, int floorId);


}