<template>
  <div>
    This is the list of homes this user has worked on
    <table class="publicHomesTable">
      <tr v-for="house in homes" v-bind:key="house.id">
        <div class="publicHomes">
          <br />
          House Name: {{ house.houseName }}<br />
          City: {{ house.city }}<br />
          State: {{ house.state }} <br />
          Foundation size: {{ house.foundationSize }} <br />
          Number of Floors: {{ house.numOfFloors }} <br />
          <!-- House Cost Params: {{ houseParamsCost[0] }} <br /> -->
          <button
            class="button-name"
            v-on:click="setActiveHouse(house.houseId)"
          >
            Checkout {{ house.houseName }}
          </button>
          <br />
          <br />
        </div>
      </tr>
    </table>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";
import HouseCostAPI from "../services/HouseCostAPI";

export default {
  data() {
    return {
      userID: this.$store.state.user.id,
      homes: [],
      houseParamsCost: [],
    };
  },
  created() {
    HomeService.getAllHousesByUserId(this.userID).then((response) => {
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
        // this.getParamsForHouseCost(newHome.houseId);
        // console.log(this.houseParamsCost);
        // this.getHouseCost(this.houseParamsCost[0]);
      }
    });
  },
  methods: {
    setActiveHouse(houseId) {
      this.$store.commit("SET_ACTIVE_HOUSE", houseId);
      this.$router.push({
        name: "view&EditFloors&Rooms",
        params: { id: houseId },
      });
    },
    getParamsForHouseCost(houseId) {
      HomeService.getHouseCostParams(houseId).then((response) => {
        const houseParamsData = response.data;
        const houseParams = {
          houseParamsCity: houseParamsData.city,
          state_abbreviation: houseParamsData.state_abbreviation,
          bathrooms: houseParamsData.bathrooms.toString(),
          bedrooms: houseParamsData.bedrooms.toString(),
          houseSize: houseParamsData.foundation_size.toString(),
          stories: houseParamsData.number_of_floors,
        };
        if (houseParams.stories > 1) {
          houseParams.stories = "multi";
        } else {
          houseParams.stories = "single";
        }
        console.log(houseParams);
        this.houseParamsCost.push(houseParams);
      });
    },
    getHouseCost(house) {
      HouseCostAPI.getCostOfHouse(house);
    },
  },
};
</script>

<style>
</style>