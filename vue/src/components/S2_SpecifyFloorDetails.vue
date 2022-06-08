<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    >
    <br />
    These are the floors in: {{ this.currentHouseName }}<br />

    <table>
      <tr v-for="floor in newFloors" v-bind:key="floor.id">
        Floor Level:
        {{
          floor.floorLevel
        }}
        <br />
          <table>
            <tr v-for="room in floors.rooms" v-bind:key="room.id">
              <div>
                Room: {{room.roomName}} <br>
                Room Size: {{room.roomSize}} <br>
                Number of Windows: {{room.numOfWindows}} <br>
              </div>

            </tr>

          </table>
        <button class="button" v-on:click="setCurrentFloor(floor.floorId)">
          Add Rooms to this Floor</button
        ><br /> <br />
        
      </tr>
    </table>
    <button class="button" v-on:click="addFloorToHouse()"> Add a floor to this house</button> <br>
    <button class="button" v-on:click="removeFloorFromHouse()">Remove the top floor from this house</button>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "specify-room-details",
  data() {
    return {
      currentHouse: this.$store.state.currentHouse,
      house_id: this.$route.params.id,
      newFloors: [],
      floors: [],
      currentHouseName: this.$store.state.currentHouse.house_name,
      highestFloor: ""
    };
  },
  created() {
    HomeService.getFloorDetails(this.house_id).then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachFloor = response.data[i];
        const newFloor = {
          floorLevel: eachFloor.floorLevel,
          floorId: eachFloor.floorId,
        };
        this.newFloors.push(newFloor);
      }
    }),
      HomeService.getHouseDetails(this.house_id).then((response) => {
        this.currentHouseName = response.data.house_name;
      });
      this.getFloors(this.house_id)
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
            floorId: eachRoom.floor_id,
            isKitchen: eachRoom.is_kitchen,
            isBathroom: eachRoom.is_bathroom,
            numOfWindows: eachRoom.number_of_windows,
            flooringTierId: eachRoom.flooring_tier_id,
          };
          floor.rooms.push(newRooms);
        }
      });
    },
    addFloorDetails() {
      HomeService.addNewRoom(this.newRooms, this.newRooms.floorId).then( // dble check
        (response) => {
          if (response.status === 200) {
            window.alert("Room Created!");
          }
        }
      );
    },
    setCurrentFloor(floorId) {
      this.$store.commit("SET_ACTIVE_FLOOR", floorId);
      this.$router.push({ name: "addRoomToFloor" });
    },
    addFloorToHouse() {
      HomeService.addFloorToHouse(this.house_id).then(
        (response) => {
          if(response.status === 200){
           window.location.reload();
          }
        }
      )

    },
    removeFloorFromHouse() {
      if (this.floors.length > 1) {
      HomeService.removeFloorFromHouse(this.floors[this.floors.length -1].floorId, this.house_id).then(
        (response) => {
          window.confirm("Are you sure you want to delete this floor?");
          if(response.status === 200){
            window.location.reload();
          }
        }
      )
      } else {window.alert("You must have at least one floor in your house");}
    },
  
  },
};
</script>

<style>
</style>