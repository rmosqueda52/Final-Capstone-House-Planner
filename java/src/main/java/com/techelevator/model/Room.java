package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {

    @JsonProperty ("room_id")
    private int roomId;
    @JsonProperty ("room_name")
    private String roomName;
    @JsonProperty ("room_size")
    private int roomSize;
    @JsonProperty ("floor_id")
    private int floorId;


    public Room(){}

    public Room(int roomId, String roomName, int roomSize, int floorId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomSize = roomSize;
        this.floorId = floorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
