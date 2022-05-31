<template>
<body id="login-body">
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <!-- <label for="username" class="sr-only">Username</label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button class="sign-in" type="submit">Sign in</button>
    </form>
  </div>
  <div id="new-account-container">
    <h3 id="create-account">Create an Account?</h3>
    <router-link id="new-account" :to="{ name: 'register' }">Sign up</router-link>
  </div>
  
</body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');
#login-body{
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  background-image: url(https://i.imgur.com/O9EDJOt.jpeg);
  background-size: cover;
  /* background-color: #023047; */

}
#new-account{
   font-family: 'Montserrat', sans-serif;
   size: 50px;
   color: whitesmoke;
   margin-top: 50px;
}
#new-account-container {
  background-color: #219ebc;
  /* padding: 150px; */
  padding-top:175px;
  padding-bottom: 750px;
  padding-left: 150px;
  padding-right: 150px;
  margin-left: 5%;
  /* align-content: baseline; */
  /* flex-grow: .4; */
  /* text-align: center; */
  /* position: absolute; */
  /* bottom: 0%; */
}
#create-account{
  font-family: 'Montserrat', sans-serif;
  color: rgb(0, 0, 0);
}
#login{
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0 150px;
  size: 50px;
  text-align: center;
  font-family: 'Montserrat', sans-serif;
}
h1{
  text-align: center;
}
#username{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: 'Montserrat', sans-serif;
  height: 25px;
  border-radius: 10px;
}
#password{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: 'Montserrat', sans-serif;
  height: 25px;
  border-radius: 10px;
}
.sign-in{
  display: flex;
  flex-direction: column;
  margin-top: 25px;
  border-radius: 10px;
}
.form-signin{
  background-color: #ffb703 1;
  padding: 50px;
  padding-bottom: 500px;
  backdrop-filter: blur(15px);
  border: 0px solid black;
  border-radius: 5px;
  position: relative;

}
</style>