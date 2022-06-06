<template>
  <div>
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
     <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <br>This is what the user sees when they click "create a new home"
    <specify-house-details class="userForm" />
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
.userForm{
padding: 100px;
display: flex;
align-self: center;

}
</style>