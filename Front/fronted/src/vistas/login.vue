<template>
  <div class="home-container">
    <header class="menu-bar">
      <div class="logo">EasyWheels</div>
      <nav class="menu">
        <button @click="goToRegister" class="menu-button">Registrarse</button>
        <button @click="goToMain" class="menu-button">Menu de Inicio</button>
      </nav>
    </header>

    <main class="content-container">
      <div class="content-box">
        <div class="header-content">
          <h1>Iniciar sesión</h1>
          <p>Ingresar información de inicio de sesión</p>
        </div>

        <div class="main-content">
          <div class="login-content">
            <div class="inputContainer">
              <input class="input-field" type="email" v-model="correo" placeholder="Ingrese correo electrónico">
              <input class="input-field" type="password" v-model="password" placeholder="Ingrese contraseña">
              <button class="action-button" v-on:click="login">Iniciar Sesión</button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

function direccionamientoUsuarioAdmin(){
  window.location.href = '/admin';
}

function direccionamientoUsuarioArrendatario(){
  window.location.href = '/arriendo';
}

function direccionamientoRegister(){
  window.location.href = '/registerArrendatario';
}

function direccionamientoMain(){
  window.location.href = '/home'
}

export default{
  data(){
    return{
      correo: '',
      password: '',
    }
  },
  methods:{
    async login(){
      if (!this.correo || !this.password) {
        alert("Debe ingresar correo y contraseña.");
        return;
      }

      const usuario = {
        'correo': this.correo,
        "password": this.password,
      };
      
      try {
        const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + 
        "usuarios/login?correo=" + this.correo + "&password=" + this.password);
        const datosUsuario = respuesta.data;

        if (datosUsuario.tipo_usuario == "Administrador"){
          alert("Credenciales válidas para administrador"),
          this.correo = '',
          this.password = '',
          direccionamientoUsuarioAdmin();
          localStorage.setItem("login", JSON.stringify(datosUsuario));
          return 0;
        }
        if (datosUsuario.tipo_usuario == "Arrendatario"){
          alert("Credenciales válidas para arrendatario")
          localStorage.setItem("userId", datosUsuario.idUsuario);
          window.location.href = '/arriendo'; 
          localStorage.setItem("login", JSON.stringify(datosUsuario));
          return 0;
        }
      } catch (error){
        alert("Datos ingresados no encontrados.");
      }
    },
    async goToRegister() {
      direccionamientoRegister();
    },
    async goToMain(){
      direccionamientoMain();
    }
  }
}
</script>

<style scoped>
/* Ajustes generales */
body, html {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(to right, #4caf50, #2f80ed);
  color: #fff;
  overflow-x: hidden;
}

.menu-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 15px 20px;
  background-color: rgba(0, 0, 0, 0.8);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

.menu {
  display: flex;
  gap: 20px; /* Separación entre los botones */
}

.menu-button {
  background-color: #2f80ed;
  color: #fff;
  padding: 10px 15px;
  font-size: 1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.menu-button:hover {
  background-color: #1c6dd0;
}

.content-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 150px;
  margin-top: 40px; 
}

.content-box {
  background-color: rgba(255, 255, 255, 0.9);
  color: #333;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
  max-width: 600px;
  width: 100%;
  display: flex;
  flex-direction: column;
}

.header-content h1 {
  font-size: 2rem;
  margin-bottom: 10px;
  color: #2f80ed;
}

.header-content p {
  font-size: 1.2rem;
  margin-bottom: 20px;
}

.inputContainer {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.input-field {
  padding: 12px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  width: 100%;
}

.action-button {
  background-color: #2f80ed;
  color: #fff;
  padding: 12px 20px;
  font-size: 1rem;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: transform 0.2s, background-color 0.3s;
}

.action-button:hover {
  background-color: #1c6dd0;
  transform: scale(1.05);
}
</style>
