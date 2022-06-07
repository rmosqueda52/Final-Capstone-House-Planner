<template>
  <div>
  
    This is the list of homes this user has worked on
    <table class="publicHomesTable">
      <tr v-for="house in homes" v-bind:key="house.id">
        <div class="publicHomes">
        <br>
        House Name: {{house.houseName}}<br>
        House Region: {{house.region}}<br>
        Foundation size: {{house.foundationSize}} <br> 
        Number of Floors: {{house.numOfFloors}} <br>
        <button class="button-name" v-on:click="setActiveHouse(house.houseId)">Checkout {{house.houseName}}</button>
        <br> <br>
        </div>
      </tr>
    </table>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  data() {
    return {
      userID: this.$store.state.user.id,
      homes: [],
    };
  },
  created() {
    HomeService.getAllHousesByUserId(this.userID).then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachHome = response.data[i];
        const newHome = {
          houseName: eachHome.house_name,
          region: eachHome.region,
          foundationSize: eachHome.foundation_size,
          houseId: eachHome.house_id,
          numOfFloors: eachHome.number_of_floors
        };
        this.homes.push(newHome);
      }
    });
  },
  methods: {
    setActiveHouse(houseId){
      this.$store.commit("SET_ACTIVE_HOUSE", houseId);
      this.$router.push({name: 'view&EditFloors&Rooms', params: { id: houseId}});
    }
  }
};
</script>

<style>


</style>