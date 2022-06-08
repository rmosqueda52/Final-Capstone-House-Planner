<template>
  <div>
      This is the Edit Room View <br>
      {{room[0].room_name}} <br>
      {{room[0].room_id}} <br>
      {{room[0].room_size}} <br>
      {{room[0].is_kitchen}} <br>
      {{room[0].is_bathroom}} <br>
      {{room[0].number_of_windows}} <br>

  </div>
</template>

<script>
import HomeService from "../services/HomeService.js";

export default {
    name: "Edit-Room-view",
    data() {
        return {
            roomId: this.$route.params.id,
            room: []
        }
    },
    created() {
        HomeService.getRoomByRoomId(this.roomId).then(
            (response) => {
               let roomInfo = []
               if(response.status === 200) {
                    roomInfo = {
                    room_name: response.data.room_name,
                    room_id: response.data.room_id,
                    room_size: response.data.room_size,
                    is_kitchen: response.data.is_kitchen,
                    is_bathroom: response.data.is_bathroom,
                    number_of_windows: response.data.number_of_windows
                    }
                }
                this.room.push(roomInfo)
            }
        )
    }
}
</script>

<style>

</style>