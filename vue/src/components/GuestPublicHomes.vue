<template>
  <div>
      <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'register' }">Create Account?</router-link>
      <br>This is the List of Public Homes
      <table>
      <tr v-for="house in homes" v-bind:key="house.id">
        <br>
        House Name:
        {{
          house.houseName
        }}<br>
        House Region:
        {{
          house.region
        }}<br>
        Foundation size:
        {{
          house.foundationSize
        }} <br> 
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
          region: eachHome.region,
          foundationSize: eachHome.foundation_size,
        };
        this.homes.push(newHome);
      }
    });
  },
};
</script>

<style>

</style>