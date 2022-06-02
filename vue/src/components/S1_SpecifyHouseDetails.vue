<template>
  <div>
      This is where we'll build a form to grab the house details input from the User<br>
  <form>

  </form>
  
  </div>
</template>

<script>
export default {
  name: "specify-house-details",
  data() {
    return{
      newHome: {
        houseName: '',
        region: '',
        foundationSize: '',
        numOfFloors: '',
        isPrivate: ''
      }
    };
  },
methods: {
  addHouseDetails() {

  }
}
}
</script>

<style>

</style>