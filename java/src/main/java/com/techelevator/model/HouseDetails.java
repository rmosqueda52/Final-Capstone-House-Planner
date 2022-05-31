package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseDetails {
    private Long houseId;
    @JsonProperty ("house_name")
    private String houseName;
    @JsonProperty("foundation_size")
    private Integer foundationSize;
    @JsonProperty ("user_id")
    private Long userId;
    private String region;
    @JsonProperty("number_of_floors")
    private int numberOfFloors;

    public HouseDetails(){}

    public HouseDetails(Long houseId, String houseName, Integer foundationSize, String region, int numberOfFloors) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.foundationSize = foundationSize;
        this.region = region;
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public String toString() {
        return "HouseDetails{" +
                "houseId=" + houseId +
                ", houseName='" + houseName + '\'' +
                ", foundationSize=" + foundationSize +
                ", userId=" + userId +
                ", region='" + region + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                '}';
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getFoundationSize() {
        return foundationSize;
    }

    public void setFoundationSize(Integer foundationSize) {
        this.foundationSize = foundationSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
}
