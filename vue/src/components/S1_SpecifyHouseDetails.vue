<template>
  <div>
    <br />
    <form v-on:submit.prevent="addHouseDetails">
      <div class="userFormStyle">
        House Name: <br />
        <input
          class="textbox"
          type="text"
          placeholder="Enter a name for this build"
          required
          v-model="newHome.house_name"
        />
        <br />
        <br />
        Region: <br />
        <input
          class="textbox"
          type="text"
          required
          placeholder="Where will your house be located?"
          v-model="newHome.region"
        /><br />
        <br />
        Foundation Size sq. ft: <br />
        <input
          class="textbox"
          type="number"
          placeholder="How large do you want your foundation to be?"
          min="1"
          required
          v-model="newHome.foundation_size"
        /><br />
        <br />
        Number of Floors in this House: <br />
        <input
          class="textbox"
          type="number"
          placeholder="How many floors?"
          min="1"
          required
          v-model="newHome.number_of_floors"
        /><br />
        <br />
        Should this house be Public or Private? <br />
        <select
          class="textbox"
          name="isPrivate"
          required
          v-model="newHome.is_private"
        >
          <option value="false">Public</option>
          <option value="true">Private</option></select
        ><br />
        <br />
        <div class="container-name">
          <a class="example_a" href="#" target="_blank" rel="nofollow"
            ><button>Submit</button></a
          >
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
  name: "specify-house-details",
  data() {
    return {
      newHome: {
        house_name: "",
        region: "",
        foundation_size: "",
        number_of_floors: "",
        is_private: "",
        user_id: this.$store.state.user.id,
      },
    };
  },
  methods: {
    addHouseDetails() {
      HomeService.addNewHouse(this.newHome).then((response) => {
        if (response.status === 200) {
          let houseId = response.data;
          this.$store.commit("SET_ACTIVE_HOUSE", houseId);
          this.$router.push({ name: "createFloorPlan" });
        }
      });
    },
  },
};
</script>

<style>
.userFormStyle {
  display: flex;
  flex-direction: column;
  margin-left: 720px;
  font-weight: bold;
  font-size: 20px;
  background-color: rgba(255, 255, 255, 0.123);
  backdrop-filter: blur(30px);
  padding: 40px;
}
.example_a {
  font-family: "Montserrat";
  color: #ffffff;
  text-transform: Capitalize;
  text-decoration: none;
  background: #000000;
  padding: 20px;
  border-radius: 5px;
  display: inline-block;
  border: none;
  transition: all 0.4s ease 0s;
}

.example_a:hover {
  background: #434343;
  letter-spacing: 1px;
  -webkit-box-shadow: 0px 5px 40px -10px rgba(0, 0, 0, 0.57);
  -moz-box-shadow: 0px 5px 40px -10px rgba(0, 0, 0, 0.57);
  box-shadow: 5px 40px -10px rgba(0, 0, 0, 0.57);
  transition: all 0.4s ease 0s;
}

.textbox {
  height: 40px;
  font-size: 10pt;
  display: flex;
  flex-direction: column;
  margin-top: 25px;
  border-radius: 10px;
  line-height: 80px;
  box-sizing: border-box;
  font-size: 20px;
}
html {
  overflow-y: scroll;
}
</style>