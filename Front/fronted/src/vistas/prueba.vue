<template>
    <div class="login-container">
      <h1>Iniciar Sesión</h1>
      <form @submit.prevent="handleLogin">
        <div>
          <label for="username">Usuario:</label>
          <input id="username" v-model="username" type="text" required />
        </div>
        <div>
          <label for="password">Contraseña:</label>
          <input id="password" v-model="password" type="password" required />
        </div>
        <button type="submit">Iniciar Sesión</button>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: '',
        password: '',
        errorMessage: '',
      };
    },
    methods: {
      handleLogin() {
        const mockUser = { username: 'admin', password: '1234' };
        if (this.username === mockUser.username && this.password === mockUser.password) {
          this.$router.push('/dashboard');
        } else {
          this.errorMessage = 'Usuario o contraseña incorrectos.';
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .login-container {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  .error {
    color: red;
  }
  </style>
  