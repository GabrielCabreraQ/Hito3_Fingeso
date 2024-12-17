
<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Datos reactivos
const publicaciones = ref([]);
const notifications = ref([]);
const selectedSection = ref('Vizualizar Publicaciones');
const menuItems = ref(['Vizualizar Publicaciones', 'Notificaciones', 'Salir']);
const showForm = ref(false);

const fechaInicio = ref('');
const fechaFin = ref('');
const idPublicacion = ref(null); // ID de la publicación seleccionada
const arrendamientoExitoso = ref(false); // Flag para saber si el arriendo fue exitoso

// Método para cambiar de sección
function changeSection(section) {
  selectedSection.value = section;
}

// Método para cargar publicaciones desde el backend
async function cargarPublicaciones() {
  try {
    const response = await axios.get('http://localhost:8080/publicaciones/visibles');
    publicaciones.value = response.data;
  } catch (error) {
    console.error('Error al cargar las publicaciones:', error);
  }
}

// Método para redirigir a registerArrendatario.vue
async function arrendar() {
  // Redirigir directamente a la vista de registro de arrendatarios
  window.location.href = '/registerArrendatario'
}


// Simular carga de notificaciones
function cargarNotificaciones() {
  notifications.value = [
    { titulo: "Oferta Imperdible!!!!", mensaje: "Cyber monday" },
    { titulo: "Recordatorio", mensaje: "No te olvides de registrarte antes de arrendar!" },
  ];
}

// Método para eliminar notificación
function deleteNotification(index) {
  notifications.value.splice(index, 1);
}

// Cargar datos al montar el componente
onMounted(() => {
  cargarPublicaciones();
  cargarNotificaciones();
});

function direccionamientoMain(){
  window.location.href = '/home'
}
async function cerrarSesion(){
      // Limpiar la sesión (si es necesario)
      localStorage.removeItem('login');
      direccionamientoMain();
    }
</script>

<template>
  <div class="container">
    <!-- Barra Superior -->
    <header class="header">
      <h1>Arriendo de Vehículos</h1>
    </header>

    <!-- Menú Lateral -->
    <aside class="sidebar">
      <nav>
        <ul>
          <li
            v-for="(item, index) in menuItems"
            :key="index"
            :class="{ active: selectedSection === item }"
            @click="changeSection(item)"
          >
            {{ item }}
          </li>
        </ul>
      </nav>
    </aside>

    <!-- Sección Principal Dinámica -->
    <main class="main-content">
      <!-- Sección Publicaciones -->
      <div v-if="selectedSection === 'Vizualizar Publicaciones'">
        <h2>Vizualización de Publicaciones</h2>
        <div class="contenedor-publicaciones">
          <div v-for="(publicacion, index) in publicaciones" :key="index" class="cuadroVehiculo">
            <div class="detalle">
              <h3>{{ publicacion.vehiculo.marca }} - {{ publicacion.vehiculo.anio }}</h3>
              <p>Precio: ${{ publicacion.precioNormal }}</p>
              <p>Categoría: {{ publicacion.vehiculo.categoria }}</p>
              <p>Modelo: {{ publicacion.vehiculo.modelo }}</p>
              <p>Tipo de transmisión: {{ publicacion.vehiculo.tipoTransmision }}</p>
              <button class="botones-arrendar" @click="arrendar(publicacion.idPublicacion)">Arrendar</button>
            </div>
          </div>
        </div>

        <!-- Formulario para arrendar -->
        <div v-if="showForm" class="formulario-arrendar">
          <h3>Formulario de Arriendo</h3>
            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" id="fechaInicio" v-model="fechaInicio" required />

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" id="fechaFin" v-model="fechaFin" required />

            <button class="boton-pagar" @click.prevent="pagar">Pagar</button> <!-- Evitar el comportamiento predeterminado -->
        </div>  
      </div>

      <!-- Sección Notificaciones -->
      <div v-if="selectedSection === 'Notificaciones'">
        <h2>Notificaciones</h2>
        <div class="tabla-notificacion-larga">
          <table class="notificaciones-recibidas">
            <thead>
              <tr>
                <th>Título</th>
                <th>Mensaje</th>
                <th>Eliminar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(notification, index) in notifications" :key="index">
                <td>{{ notification.titulo }}</td>
                <td>{{ notification.mensaje }}</td>
                <td>
                  <button class="delete-button" @click="deleteNotification(index)">🗑️</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Cerrar Sesion -->
      <div v-else-if="selectedSection === 'Salir'">
        <div class="actions">
          <button @click="cerrarSesion" class="menu-button">Salir</button>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
/* Contenedor Principal */
.container {
  display: grid;
  grid-template-columns: 250px 1fr;
  grid-template-rows: 60px 1fr;
  height: 100vh;
}

/* Barra Superior */
.header {
  grid-column: 1 / 3;
  background-color: #dcdcdc;
  color: #333;
  display: flex;
  align-items: center;
  padding: 0 20px;
  font-size: 1.5rem;
  font-weight: bold;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Menú Lateral */
.sidebar {
  background-color: #f5f5f5;
  padding: 20px 10px;
  box-shadow: 2px 0 4px rgba(0, 0, 0, 0.1);
  height: 100%;
  display: flex;
  flex-direction: column;
}

.sidebar nav ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar nav ul li {
  padding: 12px 20px;
  cursor: pointer;
  font-size: 1.1rem;
  color: #333;
  transition: background-color 0.3s ease, color 0.3s ease;
  border-radius: 5px;
  margin-bottom: 5px;
}

.sidebar nav ul li:hover {
  background-color: #218838;
  color: white;
}

.sidebar nav ul li.active {
  background-color: #218838;
  color: white;
}

.sidebar nav ul li.active:hover {
  background-color: #218838;
}

/* Contenido Principal */
.main-content {
  background-color: #fff;
  padding: 20px;
  overflow-y: auto; /* Permite el desplazamiento */
}

h2 {
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 10px;
}

/* Contenedor de Publicaciones */
.contenedor-publicaciones {
  height: 400px;
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 10px;
}

.cuadroVehiculo {
  border: 1px solid #ccc;
  text-align: center;
  padding: 10px;
  margin-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.detalle {
  font-size: 16px;
  color: black;
}

.botones-arrendar {
  margin-top: 5px;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.botones-arrendar:hover {
  background-color: #218838;
}

/* Tabla Notificaciones */
.tabla-notificacion-larga {
  max-height: 400px;
  overflow-y: auto;
}

.notificaciones-recibidas {
  width: 100%;
  border-collapse: collapse;
}

.notificaciones-recibidas th,
.notificaciones-recibidas td {
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.notificaciones-recibidas th {
  background-color: #dcdcdc;
  font-weight: bold;
  color: #000;
}

.notificaciones-recibidas td {
  color: #333;
}

.delete-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 1.2rem;
}

/* Estilos adicionales para el formulario de arrendar con scroll */
.formulario-arrendar {
  margin-top: 20px;
  padding: 20px;
  background-color: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  color: #000;
}

.formulario-arrendar label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold; 
  color: #000;
}

.formulario-arrendar input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.boton-pagar {
  padding: 10px 20px;
  background-color: #218838;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.boton-pagar:hover {
  background-color: #1e7e34;
}
</style>
