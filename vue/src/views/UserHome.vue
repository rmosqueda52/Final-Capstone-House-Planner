<template>
<div>
    This is what the user sees after they click view homes 
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