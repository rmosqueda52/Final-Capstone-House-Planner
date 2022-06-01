import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

    getAllHousesByUserId(userId) {
        return http.get(`/get-all-houses/${userId}`)
      },

}