<template>
  <div>
      This is the create a home page
      <table>
          <tr v-for="house in homes" v-bind:key="house.id">House Name: {{house.houseName}} House Region: {{house.region}} Foundation size: {{house.foundationSize}}</tr>
      </table>
  </div>
</template>

<script>
import HomeService from '../services/HomeService.js'

export default {
    data(){
        return {
            userID: 3,
            homes:[]  
        };
    },
    created() {
        HomeService.getAllHousesByUserId(this.userID).then(
            (response) => {
                for(let i = 0; i<response.data.length; i++){
                    const eachHome = response.data[i];
                    const newHome = {houseName: eachHome.house_name, region: eachHome.region, foundationSize: eachHome.foundation_size};
                    this.homes.push(newHome);
                }
                
            }
        );
    }
}
</script>

<style>

</style>