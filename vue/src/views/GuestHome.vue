<template>
  <body>
    <div class="guestHome">
      <!-- <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link>&nbsp;|&nbsp; -->
      <router-link id="create" v-bind:to="{ name: 'register' }"
        >Create Account?</router-link>

      <h1>Real Construction</h1>
      <span class="guest-welcome-text">
      <p id="welcome">Welcome to your house planner</p>
      <p id="limited-view">As a guest you can only view limited items</p>
      <p id="create-account-prompt">Create an account to customize your own home today!</p>
      </span>
      <div>
        <button
          class="guestbutton"
          v-on:click="$router.push({ name: 'guestPublicHomes' })">
          View public constructions
        </button>
        <br />
        <br />
      </div>
    </div>
  </body>
</template>

<script>
export default {
  name: "guestHome",
};
</script>

<style>
body{
  font-family: 'Montserrat', sans-serif;
}
html {
  overflow-y: hidden;
  overflow-x: hidden;
  margin: -1%;
  background: url(https://www.decoraid.com/wp-content/uploads/2021/04/bright-minimalist-living-room-design-scaled.jpeg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  
}

h1{
  text-align: left;
  margin-left: 30px;
}
.guestbutton{
  background-color: #38be3d; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 25px;
}
#create{
  position: absolute;
  padding: 20px;
  margin-left: 713px;
  font-size: 25px;
  margin-top: -2px;
  color: rgb(0, 0, 0);
  border-bottom-left-radius: 30px;
  background-color: rgba(0, 0, 0, 0.11);
  white-space: nowrap;
  backdrop-filter: blur(5px);
  
}
.guest-welcome-text{
  display: inline-block;
  font-size: 20px;
  text-align: left;
  margin-right: 1300px;
}
</style>