import axios from "axios";


export default {

  getAllHousesByUserId(userId) {
    return axios.get(`/get-all-houses/${userId}`)
  },



}