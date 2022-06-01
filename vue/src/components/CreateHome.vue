<template>
  <div>
      This is the create a home page
      <H2>{{homes.house_name}}</H2>
  </div>
</template>

<script>
import HomeService from '../services/HomeService.js'

export default {
    data(){
        return {
            userID: 3,
            homes:[{
                houseName:''
            }
            ]  
        };
    },
    created() {
        HomeService.getAllHousesByUserId(this.userID).then(
            (response) => {
                this.homes = response.data;
            }
        );
    }
}
</script>

<style>

</style>