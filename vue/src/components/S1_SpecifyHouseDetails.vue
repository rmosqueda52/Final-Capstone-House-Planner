<template>
  <div>
     <br>
  <form v-on:submit.prevent='addHouseDetails'>
    <div class="userFormStyle">
    House Name: <input type="text" required v-model='newHome.house_name'/> <br> <br> <br>
    Region: <input type="text" required  v-model='newHome.region'/><br> <br> <br>
    Foundation Size sq. ft: <input type="number" min=1 required v-model='newHome.foundation_size'/><br> <br> <br>
    Number of Floors in this House: <input type="number" min=1 required v-model='newHome.number_of_floors'/><br> <br> <br>
    Should this house be Public or Private? 
    <select name="isPrivate" required v-model='newHome.is_private'>
      <option value="false">Public</option>
      <option value="true">Private</option>
    </select><br> <br>
  <button>Submit</button>
  </div>
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
      },
    };
  },
methods: {
  addHouseDetails() {
    HomeService.addNewHouse(this.newHome).then(
      (response) => {
        if (response.status === 200) {
          let houseId = response.data
      this.$store.commit("SET_ACTIVE_HOUSE", houseId)
          this.$router.push({name:"createFloorPlan"});
        }
      }
    )
  }
}
}
</script>

<style>
.userFormStyle{
  display: flex;
  flex-direction: column;
  
  font-weight: bold;
  font-size: 20px;

}
</style>