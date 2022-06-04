<template>
  <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     <br> This is where the details for the floor will go<br>
     {{this.newRooms}} <br>
     House ID: {{this.house_id}} <br>
     You're currently working on: {{this.currentHouseName}}
     <table>
         <tr v-for="floor in newRooms" v-bind:key="floor.id">
             Floor Level: {{floor.floorLevel}} <br>

             <button v-on:click="setCurrentFloor(floor.floorId)">Add Rooms to this Floor </button><br><br>
  </tr> 
  </table> 
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js"

export default {
    name: "specify-room-details",
    data() {
        return{
           house_id: 990,     //this.$store.state.currentHouseId,
           newRooms: [],
            floors: 0,
            currentHouseName: ''
        };
    },
    created() {
        HomeService.getFloorDetails(this.house_id).then(
            (response) => {
                for(let i = 0; i<response.data.length; i++) {
                    const eachFloor = response.data[i];
                    const newRooms = {
                        floorLevel: eachFloor.floorLevel,
                        floorId: eachFloor.floorId
                    };
                    this.newRooms.push(newRooms);
                }
            }
        ),
        HomeService.getHouseDetails(this.house_id).then(
            (response) => { 
                this.currentHouseName = response.data.house_name

            }
        )
    },
    methods: {
        addFloorDetails() {
            HomeService.addNewRoom(this.newRooms, this.newRooms.floor_id).then(
                (response) => {
                    if(response.status === 200) {
                        window.alert("Room Created!")
                    }
                }
            )
        },
        setCurrentFloor(floorId) {
            this.$store.commit("SET_ACTIVE_FLOOR", floorId)
            this.$router.push({ name: 'addRoomToFloor'})
        }
    }
}
</script>

<style>

</style>