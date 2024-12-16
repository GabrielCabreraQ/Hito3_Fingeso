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
      <!-- Contenedor de texto principal con fondo verde y algo de transparencia -->
      <div class="content-box">
        <div class="header-content">
          <h1>Iniciar sesión</h1>
          <p>Ingresar información de inicio de sesion</p>
        </div>

        <!-- Contenido principal -->
        <div class="main-content">
          <div class="login-content">
              <div class="inputContainer" >
                  <input class="mailRect" type="email" v-model="this.correo" placeholder="Ingrese correo electrónico">
                  <br>
                  <br>
                  <input class="psRect" type="password" v-model="this.password" placeholder="Ingrese contraseña">
                  <br>
                  <br>
                  <button class="sesionBtn" v-on:click="login"> Iniciar Sesion </button>
              </div>
          </div>
        </div>
      </div>
    </main>
    
  </div>
</template>

<script>

import axios from 'axios';
//Direcciones
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
  // Datos prederminados
  data(){
    return{
      correo: '',
      password: '',
    }
  },
  methods:{
    //funcionamiento asincronico
    async login(){
      //Alerta en caso de que no se ingresen las credenciales correctamente
      if (!this.correo || !this.password) {
        alert("Debe ingresar correo y contraseña.");
        return;  // Detener la ejecución si faltan campos
      }

      // datos hacia el back
      const usuario = {
        'correo': this.correo,
        "password": this.password,
      };
      
      try {
        const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + 
        "usuarios/login?correo=" + this.correo + "&password=" + this.password);
        const datosUsuario = respuesta.data;

        if (datosUsuario.tipo_usuario == "Administrador"){   //user gerente
          alert("Credenciales validas para administrador"),
          this.correo = '',
          this.password = '',
          direccionamientoUsuarioAdmin();
          localStorage.setItem("login", JSON.stringify(datosUsuario));
          return 0;
        }
        if (datosUsuario.tipo_usuario == "Arrendatario"){   //user arrendatario
          alert("Credenciales validas para arrendatario")
          window.location.href = '/arriendo'; 
          localStorage.setItem("login", JSON.stringify(datosUsuario));
          return 0;
        }
        console.log(respuesta.data);
      } catch (error){
        alert("Datos ingresados no encontrados.");
      }
    },
    async goToRegister() {
      direccionamientoRegister(); // Cambia la ruta según la lógica de tu aplicación
    },
    async goToMain(){
      direccionamientoMain();
    }
  }
}

</script>

<style scoped>
.otrosBoton{
  font-size: 15px;
  color: black; /* Color inicial del texto */
  cursor: default; /* El cursor es normal por defecto */
  transition: color 0.3s ease; /* Añade una transición suave al color */
}
.otrosBoton:hover{
  color: red; /* Cambia el color del texto al pasar el mouse */
  cursor: pointer; /* Cambia el cursor a mano (puntero) */
}

.mailRect{
  height: 40px;
  width: 380px;
}

.psRect{
  height: 40px;
  width: 380px;
}

.otrosBtns{
  display: flex;
  width: 400px;
  width: 100%;
  max-width: 800px;
  justify-content: space-between;
  color: black;
}

/* Contenedor principal */
.home-container {
  font-family: Arial, sans-serif;
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr; /* Una columna en pantallas pequeñas */
  grid-template-rows: auto 1fr auto; /* Menú, contenido y pie de página */
  background-image: url('https://static.vecteezy.com/system/resources/thumbnails/017/049/365/small/dark-silver-gray-abstract-mosaic-background-vector.jpg'); /* Cambia el URL aquí */
  background-size: cover;
  background-position: center;
  background-attachment: fixed; /* Esto hace que el fondo se quede fijo al hacer scroll */
  margin: 0;
  color: #fff;
  width: 110%; /* Asegura que el contenedor ocupe todo el ancho */
  height: 100%; /* Asegura que el contenedor ocupe todo el alto */
  overflow: hidden; /* Previene el scroll de fondo que pueda generar el contenido */
}

/* Menú superior */
.menu-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  background-color: rgba(0, 0, 0, 0.8);
  width: 100%; /* Asegura que el menú ocupe todo el ancho */
  position: fixed; /* Fija el menú en la parte superior */
  top: 0;
  left: 0;
  z-index: 100; /* Asegura que el menú quede por encima de otros elementos */
}

.logo {
  font-size: 28px;
  font-weight: bold;
  color: #fff;
}

.menu {
  display: flex;
  gap: 20px;
}

.menu-button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.menu-button:hover {
  background-color: #45a049;
}

/* Contenedor del contenido */
.content-container {
  display: grid;
  grid-template-columns: 1fr; /* Una sola columna para contenido */
  justify-items: center;
  padding: 20px;
  text-align: center;
  grid-column: 1 / -1; /* Ocupa toda la columna */
  margin-top: 80px; /* Da espacio para el menú fijo */
}

/* Contenedor con fondo verde y algo de transparencia */
.content-box {
  background-color: rgba(76, 175, 80, 0.7); /* Fondo verde con 70% de opacidad */
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 800px; /* Limita el tamaño */
}

/* Texto del contenido */
.header-content h1 {
  font-size: 3rem;
  margin-bottom: 20px;
}

.header-content p {
  font-size: 1.5rem;
  margin-bottom: 30px;
}

.main-content {
  display: grid;
  grid-template-columns: 1fr;
  gap: 30px;
}

.main-content section {
  margin-bottom: 30px;
}

.main-content h2 {
  font-size: 2rem;
  margin-bottom: 15px;
}

.main-content ul {
  list-style: none;
  padding: 0;
}

.main-content ul li {
  margin: 10px 0;
  font-size: 1.2rem;
}

/* Botón Buscar Autos con fondo negro */
.search-button {
  padding: 15px 30px;
  background-color: #000; /* Fondo negro */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: all 0.3s ease;
}

.search-button:hover {
  background-color: #444;
}

/* Responsive */
@media (max-width: 768px) {
  .menu-bar {
    flex-direction: column;
    align-items: flex-start;
  }

  .menu {
    flex-direction: column;
    gap: 10px;
  }

  .header-content h1 {
    font-size: 2rem;
  }

  .header-content p {
    font-size: 1.2rem;
  }
}

/* Asegurando que el fondo ocupe toda la pantalla */
html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
}

#app {
  height: 100%;
  width: 100%;
}
</style>
