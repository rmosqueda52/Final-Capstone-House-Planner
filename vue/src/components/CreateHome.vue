<template>
  <div>
      This is the create a home page
  </div>
</template>

<script>
import HomeService from '../services/HomeService.js'

export default {
    data(){
        return {
            Homes:[]
        };
    },
    created() {
        HomeService.getAllHouses().then(
            (response) => {
                this.homes = response.data;
            }
        );
    }
}
</script>

<style>

</style>