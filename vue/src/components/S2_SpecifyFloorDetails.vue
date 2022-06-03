<template>
  <div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     <br> This is where the details for the floor will go
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>