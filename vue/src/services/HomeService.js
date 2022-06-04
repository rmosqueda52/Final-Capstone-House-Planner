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
  getFloorDetails(houseId){
    return axios.get(`/get-all-floors/${houseId}`)
  },
  addNewRoom(newRoom, floorId) {
    return axios.post(`/add-room/${floorId}`,newRoom)
  },
  getHouseDetails(houseId) {
    return axios.get(`/get-house-details/${houseId}`)
  }
  
}