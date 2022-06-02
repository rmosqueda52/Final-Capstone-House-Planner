package com.techelevator.model;

public class Room {

    private int roomId;
    private String roomName;
    private int roomSize;
    private int floorNum;


    public Room(){}

    public Room(int roomId, String roomName, int roomSize, int floorNum) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomSize = roomSize;
        this.floorNum = floorNum;
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

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }
}
