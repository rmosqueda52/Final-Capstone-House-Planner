<template>
  <div> <br> <br>
       You're currently looking at: {{this.currentHouseName}}
     <table>
         <tr v-for="floor in floors" v-bind:key="floor.id">
             Floor Level: {{floor.floorLevel}} <br>


  </tr> 
  </table> 
  </div>
</template>

<script>
import HomeService from "../services/HomeService.js"

export default {
    name: "view-floor-and-room-details",
    data() {
        return{
            house_id: this.$store.state.currentHouseId,
            floors: [],
            numOfFloors: 0,
            currentHouseName: ''
        };
    },
    created() {
        HomeService.getFloorDetails(this.house_id).then(
            (response) => {
                for(let i = 0; i<response.data.length; i++) {
                    const eachFloor = response.data[i];
                    const newFloor = {
                        floorLevel: eachFloor.floorLevel,
                        floorId: eachFloor.floorId
                    };
                    this.floors.push(newFloor);
                }
            }
        ),
        HomeService.getHouseDetails(this.house_id).then(
            (response) => { 
                this.currentHouseName = response.data.house_name

            }
        )
    }
}
</script>

<style>

</style>