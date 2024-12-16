<template>
  <div class="home-container">
    <header class="menu-bar">
      <div class="logo">EasyWheels</div>
      <nav class="menu">
        <button @click="goToLogin" class="menu-button">Iniciar Sesión</button>
        <button @click="goToCatalog" class="menu-button">Catálogo</button>
        <button @click="goToCustomerService" class="menu-button">Servicio al Cliente</button>
      </nav>
    </header>

    <main class="content-container">
      <!-- Contenedor de texto principal con fondo verde y algo de transparencia -->
      <div class="content-box">
        <div class="header-content">
          <h1>Registro de arrendatario</h1>
          <p>Ingresar información de registro</p>
        </div>

        <!-- Contenido principal -->
        <div class="main-content">
          <div class="login-content">
              <div class="inputContainer" >
                  <input class="mailRect"  v-model="nombreUsuario" placeholder="Ingrese nombre">
                  <br>
                  <input class="mailRect" type="email" v-model="correoUsuario" placeholder="Ingrese correo electrónico">
                  <br>
                  <input class="psRect" type="password" v-model="contraseniaUsuario" placeholder="Ingrese contraseña">
                  <br>
                  
                  <input class="mailRect" v-model="telefonoUsuario" placeholder="Ingrese número telefónico">
                  <br>
                  <label>Fecha nacimiento: </label>
                  <input class="fechaNac" type="email" v-model="correo" placeholder="año">
                  <input class="fechaNac" type="email" v-model="correo" placeholder="mes">
                  <input class="fechaNac" type="email" v-model="correo" placeholder="día">
                  <br>
                  <br>
                  <button class="sesionBtn" v-on:click="registerUser()"> Registro </button>
              </div>
              <div class="otrosBtns">
                  <div class="otrosBoton" v-on:click="handleChange">Anónimo</div>
              </div>
          </div>
        </div>
      </div>
    </main>

    <!-- Aquí se agrega el RouterView para la navegación de rutas -->
    <RouterView />
  </div>
</template>

<script>

import axios from 'axios';
//Direcciones
function direccionamientoUsuarioAdmin(){
  window.location.href = '/admin';
}
function direccionamientoUsuarioArrendatario(){
  window.location.href = '/arrendatario';
}
function direccionamientoUsuarioAnonimo(){
  window.location.href = '/anonimo';
}


export default{
  // Datos prederminados
  data(){
    return{
      correo: '',
      contraseniaUsuario: '',
    }
  },
  methods:{
    //funcionamiento asincronico
    async registerUser(){
      if (this.contraseniaUsuario != '' & this.correoUsuario !=''){
        const nuevoUsuario = {
          "correoUsuario": this.correoUsuario,
          "contraseniaUsuario": this.contraseniaUsuario,
        }
        try{
          const registro = await axios.post(import.meta.env.VITE_BASE_URL + "arrendatario/register",nuevoUsuario);
          console.log(registro)   // para confirmar que se crea el usuario
          alert("Usuario arrendatario creado con exito")
        } catch (error) {
          alert("No se pudo registrar el arrendatario")
        }
      }
      else{
        alert("No hay datos ingresados")
      }
    },

    goToLogin() {
      this.$router.push('/login'); // Cambia la ruta según la lógica de tu aplicación
    },
    goToCatalog() {
      this.$router.push('/catalogo'); // Cambia la ruta según la lógica de tu aplicación
    },
    goToCustomerService() {
      this.$router.push('/servicio-cliente'); // Cambia la ruta según la lógica de tu aplicación
    },
    startSearch() {
      this.$router.push('/buscar'); // Cambia la ruta según la lógica de tu aplicación
    },
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

.fechaNac{
  height: 25px;
  width: 80px;

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
  background-image: url('https://media.istockphoto.com/id/959093634/es/vector/vector-abstracto-borrosa-pastel-colores-suave-gradiente-de-fondo.jpg?s=612x612&w=0&k=20&c=atXACiaxrVZFoTAv3S8pEgwyAd8Mn-zgY5UCkzD8eJo='); /* Cambia el URL aquí */
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
