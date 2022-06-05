<template>
  <div>
      This is where we'll add room details to the floor <br>
      You're currently working on floor:  <br>
      {{this.floorId}} <br>
      {{this.rooms}}

  </div>
</template>

<script>
import HomeService from "../services/HomeService.js"

export default {
    name: "add-room-too-floor",
    data() {
        return {
            floorId: this.$store.state.currentFloorId,
            rooms:[],
        };
    },
    created() {
        HomeService.getRoomsByFloorId(this.floorId).then(
            (response) => { 
                for(let i = 0; i<response.data.length; i++) {
                    const eachRoom = response.data[i];
                    const newRooms = {
                        roomId: eachRoom.room_id,
                        roomName: eachRoom.room_name
                    };
                    this.rooms.push(newRooms)
                }

            }
        )
    }
}
</script>

<style>

</style>