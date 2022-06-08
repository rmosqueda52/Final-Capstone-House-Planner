<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      >Logout</router-link
    >
    Please add room details to the floor <br />
  
    <form v-on:submit.prevent="addRoomToFloor">
      Room Name: <input type="text" required v-model="newRoom.room_name" />
      <br />
      Room Size: Sq ft
      <input type="number" min="1" required v-model="newRoom.room_size" />
      <br />
      Is this room a Kitchen?
      <input type="checkbox" value="true" v-model="newRoom.is_kitchen" /><label
        >Yes</label
      ><br />
      is this room a Bathroom?
      <input type="checkbox" value="true" v-model="newRoom.is_bathroom" /><label
        >Yes</label
      ><br />
      How many windows will be in this room?
      <input
        type="number"
        min="0"
        required
        v-model="newRoom.number_of_windows"
      />
      <br />
      <button class="button">Add New Room</button>
    </form>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "add-room-too-floor",
  data() {
    return {
      floorId: this.$store.state.currentFloorId,
      rooms: [],
      newRoom: {
        room_name: "",
        room_size: "",
        is_kitchen: false,
        is_bathroom: false,
        number_of_windows: "",
        floor_id: this.$store.state.currentFloorId,
        flooring_tier_id: 1,
      },
    };
  },
  created() {
    HomeService.getRoomsByFloorId(this.floorId).then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachRoom = response.data[i];
        const newRooms = {
          roomId: eachRoom.room_id,
          roomName: eachRoom.room_name,
        };
        this.rooms.push(newRooms);
      }
    });
  },
  methods: {
    addRoomToFloor() {
      HomeService.addNewRoom(this.newRoom, this.floorId).then((response) => {
        if (response.status === 200) {
          window.alert("Room Created");
          this.$router.push({ name: "createFloorPlan" });
        }
      });
    },
  },
};
</script>

<style>
</style>