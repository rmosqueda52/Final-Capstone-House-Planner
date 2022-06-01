import axios from "axios";

export default {

    getAllHouses() {
        return axios.get('/get-all-houses/')
      },
// Need to clarify House ID in Server Side

}