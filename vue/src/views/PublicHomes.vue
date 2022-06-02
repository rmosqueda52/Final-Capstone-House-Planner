<template>
  <div>
      This is what the user sees when they click public constructions
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