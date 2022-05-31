<template>
<body id="register-body">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 id="register-header">Create Account</h1> <!--class="h3 mb-3 font-weight-normal"-->
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <!-- <label for="username" class="sr-only">Username</label> -->
      <input
        type="text"
        id="regusername"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="regpassword"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <input
        type="radio"
        id="home-buyer"
        class="form-control"
        v-model="user.role"
        name="Role"
        value="Home_Buyer"
        required
      />
      <label for="role" id="home-buyer-label">Home Buyer</label><br>
      <input
        type="radio"
        id="system-admin"
        class="form-control"
        v-model="user.role"
        name="Role"
        value="System_Admin"
        required
      />
      <label for="role" id="system-admin-label">System Administrator</label>
      <button id="create-account" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button><br>
      <router-link id="login-link" :to="{ name: 'login' }">Already have an account?</router-link>
      
    </form>
  </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
#register-body {
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  background-color: #023047;
  padding-bottom: 600px;
}
#register-header {
  color: white;
  padding-top: 150px;
}
#register{
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0 150px;
  size: 50px;
  text-align: center;
  font-family: 'Montserrat', sans-serif;
}
#regusername{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: 'Montserrat', sans-serif;
  height: 25px;
  border-radius: 10px;
  }
#regpassword{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: 'Montserrat', sans-serif;
  height: 25px;
  border-radius: 10px;
}
#confirmPassword{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-family: 'Montserrat', sans-serif;
  height: 25px;
  border-radius: 10px;
}
#home-buyer{
  margin-left:-102px;
}
#system-admin{
  margin-left: -30px;
}
#home-buyer-label{
  color:whitesmoke;
}
#system-admin-label{
  color: whitesmoke;
}
#create-account{
  display: flex;
  flex-direction: column;
  margin-top: 25px;
  border-radius: 10px;
}
#login-link{
   font-family: 'Montserrat', sans-serif;
   size: 50px;
   color: whitesmoke;
   align-content: top;
}

</style>
