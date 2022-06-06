<template>
  <div>
    This is the GuestFloor page
    <div>
      <br />
      <br />
      You're currently looking at: {{ this.currentHouseName }}
      <table>
        <tr v-for="floor in floors" v-bind:key="floor.id">
          Floor Level:{{
            floor.floorLevel
          }}
          <table>
            <tr v-for="room in rooms" v-bind:key="room.id">
              Room:
              {{
                rooms.room
              }}
            </tr>
          </table>
          <br />
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
// import HomeService from "../services/HomeService.js";

export default {
  name: "guest-view-floor-plans",
  data() {
    return {
      house_id: this.$store.state.currentHouseId,
      floors: [],
      numOfFloors: 0,
      currentHouseName: "",
      rooms: [],
    };
  }

//   created() {
//     HomeService.getFloorDetails(this.house_id).then((response) => {
//       for (let i = 0; i < response.data.length; i++) {
//         const eachFloor = response.data[i];
//         const newFloor = {
//           floorLevel: eachFloor.floorLevel,
//           floorId: eachFloor.floorId,
//           rooms: [],
//         };

//         this.floors.push(newFloor);
//         console.log('added new floor')
//       }
//     })
    
//     for( let i = 0; i <this.floors.length; i++){
//         HomeService.getRoomsByFloorId(floorId).then((roomResponse) => {
//           for (let i = 0; i < roomResponse.data.length; i++) {
//             const eachRoom = roomResponse.data[i];
//             const newRooms = {
//               roomId: eachRoom.room_id,
//               roomName: eachRoom.room_name,
//               roomSize: eachRoom.room_size,
//               floorID: eachRoom.floor_id,
//               isKitchen: eachRoom.is_kitchen,
//               isBathroom: eachRoom.is_bathroom,
//               numOfWindows: eachRoom.number_of_windows,
//               flooringTierId: eachRoom.flooring_tier_id,
//             };
//             newFloor.rooms.push(newRooms);
//           }
//         })
//       }
//       HomeService.getHouseDetails(this.house_id).then((response) => {
//         this.currentHouseName = response.data.house_name;
//       });
//   },
//   // methods: {
//   //   getRoomsFromFloor(floorId){
//   //   HomeService.getRoomsByFloorId(floorId).then(
//   //    (response) => {
//   //      for(let i=0; i<response.data.length; i++){
//   //        const eachRoom = response.data[i];
//   //        const newRooms = {
//   //          roomId: eachRoom.room_id,
//   //          roomName: eachRoom.room_name,
//   //          roomSize: eachRoom.room_size,
//   //          floorID: eachRoom.floor_id,
//   //          isKitchen: eachRoom.is_kitchen,
//   //          isBathroom: eachRoom.is_bathroom,
//   //          numOfWindows: eachRoom.number_of_windows,
//   //          flooringTierId: eachRoom.flooring_tier_id
//   //        };
//   //        this.floors.push(newRooms);
//   //        }
//   //    }
//   //   )
//   //   }
//   // }
};
</script>

<style>
</style>