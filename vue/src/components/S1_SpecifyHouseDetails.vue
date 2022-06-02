<template>
  <div>{{this.newHome}}
      This is where we'll build a form to grab the house details input from the User<br>
  <form v-on:submit.prevent='addHouseDetails'>
    House Name: <input type="text" required v-model='newHome.house_name'/> <br>
    Region: <input type="text" required  v-model='newHome.region'/><br>
    Foundation Size: <input type="number" min=1 required v-model='newHome.foundation_size'/><br>
    Number of Floors in this House: <input type="number" min=1 required v-model='newHome.number_of_floors'/><br>
    Should this house be Public or Private? 
    <select name="isPrivate" required v-model='newHome.isPrivate'>
      <option value="false">Public</option>
      <option value="true">Private</option>
    </select><br> <br>
  <button>Submit</button>
  </form>
  
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "specify-house-details",
  data() {
    return{
      newHome: {
        house_name: '',
        region: '',
        foundation_size: '',
        number_of_floors: '',
        is_private: '',
        user_id: this.$store.state.user.id
      }
    };
  },
methods: {
  addHouseDetails() {
    HomeService.addNewHouse(this.newHome).then(
      (response) => {
        if (response.status === 200) {
          this.$router.push({name:"createFloorPlan"});
        }
      }
    )
  }
}
}
</script>

<style>

</style>