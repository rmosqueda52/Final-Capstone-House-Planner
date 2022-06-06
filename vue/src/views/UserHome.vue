<template>

<div>
  <router-link class="homeButton" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link class="logoutButton" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
   <br> This is what the user sees after they click view homes 
    <view-homes />
</div>

</template>


<script>
import ViewHomes from "../components/ViewHomes.vue";

export default {
  name: "userHome",
  components: {
    ViewHomes,
  },
};

</script>

<style>

</style>