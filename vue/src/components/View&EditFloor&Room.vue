<template>
  <div>
    <div class="top-links">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link>
    </div>
    <br />
    <br />
    You're currently looking at: {{ this.currentHouseName }}
    <table class="floor-details-user-homes">
      <tr v-for="floor in floors" v-bind:key="floor.id">
        <div class="floor-level">
        Floor Level:
        {{
          floor.floorLevel
        }}
        </div>
        <table>
            <tr v-for="room in floor.rooms" v-bind:key="room.id">
                <div>
                    Room: {{ room.roomName}} <button class="button">Edit this Room</button> <br>
                    Room Size: {{ room.roomSize}} <br>
                    number of Windows: {{room.numOfWindows}} <br>
                    <br>
                </div>

            </tr>

        </table>
        <br />
      </tr>
    </table>
    <button class="button" v-on:click="addFloorToHouse()">Add a Floor to this House</button> <br>
    <button class="button"> Remove the Top Floor from this House</button>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "view-and-edit-floors-and-rooms",
  data() {
    return {
      house_id: this.$route.params.id,
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
    addFloorToHouse() {
      HomeService.addFloorToHouse(this.house_Id).then(
        (response) => {
          if(response.status === 200){
          window.alert("Floor Created");
          window.location.reload();
          }
        }
      );
    }
  },
};
</script>

<style>
.top-links{
text-align: left;
margin-left: 20px;
margin-top: 20px;
font-family: 'Montserrat';
font-weight: bold;
font-size: 20px;
color: black;
}
.floor-details-user-homes{
align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 50px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: relative;
  margin-top: 25px;
  padding: 60px;
  font-size: 20px;
  font-weight: bold;
}
</style>