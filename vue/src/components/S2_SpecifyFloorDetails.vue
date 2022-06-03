<template>
  <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     <br> This is where the details for the floor will go<br>
     {{this.newRooms}}
<form v-on:submit.prevent='addFloorDetails'>
   
  <button>Submit</button>
  </form>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js"

export default {
    name: "specify-room-details",
    data() {
        return{
            newRooms: [ {
                room_name: '',
                room_size: '',
                floor_id: ''
            }
            ]
        };
    },
    created() {
        HomeService.get
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