<template>
  <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     <br> This is where the details for the floor will go<br>
     {{this.newRooms}} <br>
     House ID: {{this.house_id}}
     <table>
         <tr v-for="floor in newRooms" v-bind:key="floor.id">
        <form v-on:submit.prevent='addFloorDetails'>
     Room Name: <input type="text" required v-model='newRooms.room_name'/><br>
     room Size sq. ft: <input type="number" min=1 required v-model="newRooms.room_size"/> <br>

    <button>Submit</button>
  
  </form>
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
           newRooms: [{
               room_name: '',
           }
           ],
            floors: 0
        };
    },
    created() {
        HomeService.getFloorDetails(this.house_id).then(
            (response) => {
                console.log(response.data)
                for(let i = 0; i<response.data.length; i++) {
                    const eachFloor = response.data[i];
                    const newRooms = {
                        floorLevel: eachFloor.floorLevel
                    };
                    this.newRooms.push(newRooms);
                }
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
        }
    }
}
</script>

<style>

</style>