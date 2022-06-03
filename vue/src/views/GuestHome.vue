<template>
   <div class="guestHome">
       <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'register' }">Create Account?</router-link>
    <h1>Home</h1>
    <p>Welcome to Build a Home!</p>
    <div>
        <button class="button" v-on:click="$router.push({ name: 'guestPublicHomes'})">
           View public constructions</button> <br> <br>
    </div>
  </div>
</template>

<script>
export default {
    name: "guestHome"
}
</script>

<style>

</style>