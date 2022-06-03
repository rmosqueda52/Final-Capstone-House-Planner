<template>
  <div class="home">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    <h1>Home</h1>
    <p>Welcome to Build a Home!</p>
    <div>
        <button class = "button" v-on:click="$router.push({ name: 'userHomes'})">
           View your homes under construction</button> <br> <br>
        <button class = "button" v-on:click="$router.push({ name: 'publicHomes'})">
           View public constructions</button> <br> <br>
        <button class = "button"  v-on:click="$router.push({ name: 'createNewHome'})">
           Create a new Home</button>
    </div>
  </div>
</template>

<script>

export default {
  name: "home",
  
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
