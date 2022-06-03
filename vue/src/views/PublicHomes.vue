<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <br>This is what the user sees when they click public constructions
      <view-public-homes />
  </div>
</template>

<script>
import viewPublicHomes from '../components/ViewPublicHomes.vue';
export default {
  name: "publicHomes",
  components: {
    viewPublicHomes,
  }
}
</script>

<style>

</style>