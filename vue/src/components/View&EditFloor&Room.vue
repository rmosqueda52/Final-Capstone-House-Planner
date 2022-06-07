<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    ><br />
    <br />
    You're currently looking at: {{ this.currentHouseName }}
    <table>
      <tr v-for="floor in floors" v-bind:key="floor.id">
        Floor Level:
        {{
          floor.floorLevel
        }}
        <table>
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
                <div>
                    Room: {{ room.roomName}} <br>
                    Room Size: {{ room.roomSize}} <br>
                    number of Windows: {{room.numOfWindows}} <br>
                    <br>
                </div>

            </tr>

        </table>
        <br />
      </tr>
    </table>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "view-and-edit-floors-and-rooms",
  data() {
    return {
      house_id: this.$store.state.currentHouseId,
      floors: [],
      numOfFloors: 0,
      currentHouseName: "",
    };
  },
  created() {
    this.getHouseDetails();
    this.getFloors(this.house_id);
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
          this.getRooms(newFloor);
        }
      });
    },
    getRooms(floor) {
      HomeService.getRoomsByFloorId(floor.floorId).then((roomResponse) => {
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