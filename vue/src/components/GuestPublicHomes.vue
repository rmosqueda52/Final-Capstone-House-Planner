<template>
<body>
  <div>
    <div class="in-and-out">
    <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link
    >&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'register' }">Create Account?</router-link>
    <br />This is the List of Public Homes
    </div>
    <table>
      <tr v-for="house in homes" v-bind:key="house.id">
        <div class="homes">
          <br />
          House Name:{{ house.houseName }}<br />
          <br />
          House Region:{{ house.region }}<br />
          <br />
          Foundation size:{{ house.foundationSize }} <br />
          <br />
          Number of Floors: {{ house.numOfFloors }} <br />
          <br />
          <button role="button" class="button-name" v-on:click="setActiveHouse(house.houseId)">
            Checkout {{ house.houseName }}
          </button>
        </div>
      </tr>
    </table>
  </div>
</body>
  
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
          numOfFloors: eachHome.number_of_floors,
          houseId: eachHome.house_id,
        };
        this.homes.push(newHome);
      }
    });
  },
  methods: {
    setActiveHouse(houseId) {
      this.$store.commit("SET_ACTIVE_HOUSE", houseId);
      this.$router.push({ name: "GuestViewFloor" });
    },
  },
};
</script>

<style>
table{
  align-items: center;
  /* padding: 100px; */
  background-color: rgba(54, 148, 66, 0.397);
  margin-left: 690px;
  border-radius: 107px;
  padding-bottom: 60px;
  backdrop-filter: blur(10px);
  color: rgb(0, 0, 0);
  position: absolute;
  margin-top: 55px;
  padding: 60px;
    
}
.in-and-out{
  /* position: absolute; */
  /* margin-top: 390px; */
  /* margin-left: 829px; */
  
}
.homes{
  font-size: 20px;
  text-align: center;
  

  
  
}

/* From uiverse.io by @Custyyyy */
.button-name {
 align-items: center;
 appearance: none;
 background-color: #FCFCFD;
 border-radius: 10px;
 border-width: 0;
 box-shadow: rgba(45, 35, 66, 0.2) 0 2px 4px,rgba(45, 35, 66, 0.15) 0 7px 13px -3px,#D6D6E7 0 -3px 0 inset;
 box-sizing: border-box;
 color: #000000;
 cursor: pointer;
 display: inline-flex;
 height: 48px;
 justify-content: center;
 line-height: 1;
 list-style: none;
 overflow: hidden;
 padding-left: 16px;
 padding-right: 16px;
 position: relative;
 text-align: left;
 text-decoration: none;
 transition: box-shadow .15s,transform .15s;
 user-select: none;
 -webkit-user-select: none;
 touch-action: manipulation;
 white-space: nowrap;
 will-change: box-shadow,transform;
 font-size: 18px;
 margin-bottom: 30px;
}

.button-name:focus {
 box-shadow: #D6D6E7 0 0 0 1.5px inset, rgba(45, 35, 66, 0.4) 0 2px 4px, rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #D6D6E7 0 -3px 0 inset;
}

.button-name:hover {
 box-shadow: rgba(45, 35, 66, 0.3) 0 4px 8px, rgba(45, 35, 66, 0.2) 0 7px 13px -3px, #D6D6E7 0 -3px 0 inset;
 transform: translateY(-2px);
}

.button-name:active {
 box-shadow: #D6D6E7 0 3px 7px inset;
 transform: translateY(2px);
}
br {
  content: "";
  margin: 2em;
  display: block;
  font-size: 24%;
}
</style>