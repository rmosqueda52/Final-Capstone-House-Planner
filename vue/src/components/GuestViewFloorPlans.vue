<template>
  <div>
    This is the GuestFloor page
    <div>
      <br />
      <br />
      You're currently looking at: {{ this.currentHouseName }}
      <table>
        <tr v-for="floor in floors" v-bind:key="floor.id">
          Floor Level:{{
            floor.floorLevel
          }}
          <table>
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
              Room:
              {{
                room.roomName
              }}
            </tr>
          </table>
          <br />
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import HomeService from "../services/HomeService.js";
export default {
  name: "guest-view-floor-plans",
  data() {
    return {
      house_id: this.$store.state.currentHouseId,
      floors: [],
      numOfFloors: 0,
      currentHouseName: "",
    };
  },
  created() {
    console.log("created");
    this.getHouseDetails();
    this.getFloors(this.house_id);
    for( let i = 0; i <this.floors.length; i++){
      this.getRooms(this.floors[i]);
    }
  },
  methods: {
    getFloors(houseId) {
      HomeService.getFloorDetails(houseId).then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          const eachFloor = response.data[i];
          const newFloor = {
            floorLevel: eachFloor.floorLevel,
            floorId: eachFloor.floorId,
            rooms: [],
          };
          this.floors.push(newFloor);
          this.getRooms(newFloor.floorId);
        }
      });
    },
    getRooms(floorId) {
      HomeService.getRoomsByFloorId(floorId).then((roomResponse) => {
        for (let i = 0; i < roomResponse.data.length; i++) {
          const eachRoom = roomResponse.data[i];
          const newRooms = {
            roomId: eachRoom.room_id,
            roomName: eachRoom.room_name,
            roomSize: eachRoom.room_size,
            floorID: eachRoom.floor_id,
            isKitchen: eachRoom.is_kitchen,
            isBathroom: eachRoom.is_bathroom,
            numOfWindows: eachRoom.number_of_windows,
            flooringTierId: eachRoom.flooring_tier_id,
          };
          let floor = null;
          for (let k = 0; k < this.floors.length; k++) {
            if (this.floors[k].floorId == floorId) {
              floor = this.floors[k];
              break;
            }
          }
          floor.rooms.push(newRooms);
        }
      });
    },
    getHouseDetails() {
      HomeService.getHouseDetails(this.house_id).then((response) => {
        this.currentHouseName = response.data.house_name;
      });
    },
  },
};
</script>
<style>
</style>