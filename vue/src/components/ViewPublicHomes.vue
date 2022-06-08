<template>
  <div>
    This is the List of Public Homes
    <table class="publicHomesTable">
      <tr v-for="house in homes" v-bind:key="house.id">
        <div class="publicHomes">
          <br />
          House Name: {{ house.houseName }}<br />
          <br />
          City: {{ house.city }}<br />
          <br />
          State: {{ house.state }}<br />
          <br />
          Foundation size: {{ house.foundationSize }} <br />
          <br />
          Number of Floors: {{ house.numOfFloors }} <br />
          <br />
          <button
            role="button"
            class="button-name"
            v-on:click="setActiveHouse(house.houseId)"
          >
            Checkout {{ house.houseName }}
          </button>
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
    HomeService.getAllPublicHouses().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        const eachHome = response.data[i];
        const newHome = {
          houseName: eachHome.house_name,
          city: eachHome.city,
          state: eachHome.state_abbreviation,
          foundationSize: eachHome.foundation_size,
          houseId: eachHome.house_id,
          numOfFloors: eachHome.number_of_floors,
        };
        this.homes.push(newHome);
      }
    });
  },
  methods: {
    setActiveHouse(houseId) {
      this.$store.commit("SET_ACTIVE_HOUSE", houseId);
      this.$router.push({ name: "viewCurrentFloorAndRoomDetails" });
    },
  },
};
</script>

<style>
.publicHomesTable {
  align-items: center;
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 721px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: absolute;
  margin-top: 25px;
  padding: 60px;
}
.publicHomes {
  font-size: 20px;
  text-align: center;
}
</style>