<template>
  <div>
      This is what the user sees when they click "create a new home"
    <specify-house-details />
  </div>
</template>

<script>
import SpecifyHouseDetails from '../components/S1_SpecifyHouseDetails.vue'
export default {
    name: "CreateNewHome",
    components: {
        SpecifyHouseDetails,
    }
}
</script>

<style>

</style>