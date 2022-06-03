import axios from "axios";


export default {

  getAllHousesByUserId(userId) {
    return axios.get(`/get-all-houses/${userId}`)
  },
  getAllPublicHouses() {
    return axios.get('/guest')
  },
  addNewHouse(newHouse) {
    return axios.post('/create', newHouse)
  },
  getFloorDetails(){

  },
  addNewRoom(newRoom) {
    return axios.post('/',newRoom)
  }
}