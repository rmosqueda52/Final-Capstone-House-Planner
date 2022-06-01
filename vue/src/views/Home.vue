<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <create />
  </div>
</template>

<script>
import Create from '../components/CreateHome.vue';

export default {
  name: "home",
  components: {
    Create
  }
};
</script>
<style>

</style>
