<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>

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
html {
  margin: 0%;
}
</style>
