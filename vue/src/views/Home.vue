<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div>
        <button class = "button" v-on:click="$router.userHome">View your homes under construction</button> <br> <br>
        <button class = "button">View public constructions</button> <br> <br>
        <button class = "button">Create a new Home</button>
    </div>

    
    <view-homes />
  </div>
</template>

<script>
import ViewHomes from "../components/ViewHomes.vue";

export default {
  name: "home",
  components: {
    ViewHomes,
  },
};
</script>


<style>
.button{
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 25px;
}
</style>
