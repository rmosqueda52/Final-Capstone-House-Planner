<template>
  <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     <br> This is where the details for the floor will go<br>
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

        };
    },
    methods: {
        addFloorDetails() {
            HomeService.addNewRoom().then(
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