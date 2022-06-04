<template>
  <body>
    <div class="guestHome">
      <!-- <router-link v-bind:to="{ name: 'guestHome' }">Home</router-link>&nbsp;|&nbsp; -->
      <!-- <router-link id="create" v-bind:to="{ name: 'register' }"
        >Create Account?</router-link> -->

      <h1>Home</h1>
      <p>Welcome to Build a Home!</p>
      <div>
        <button
          class="button"
          v-on:click="$router.push({ name: 'guestPublicHomes' })"
        >
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

html {
  overflow-y: hidden;
  overflow-x: hidden;
  margin: -1%;
  
}
body {
 background-color: cadetblue;
}

</style>