<template>
  <div class="container">
    <!-- Barra Superior -->
    <header class="header">
      <h1>Inventario</h1>
    </header>

    <!-- Menú Lateral -->
    <aside class="sidebar">
      <div class="user-info">
        <div class="user-name">Nombre del Usuario</div>
      </div>
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
      <!-- Gestión de Publicaciones -->
      <div v-if="selectedSection === 'Gestión de Publicaciones'">
        <h2>Gestión de Publicaciones</h2>
        
        <!-- Botón para crear nueva publicación -->
        <div class="actions">
          <button class="create-button" @click="showForm = true">Nueva publicación</button>
        </div>

        <!-- Si showForm es true, mostrar formulario -->
        <div v-if="showForm">
          <form class="publication-form">
            <div class="form-group">
              <label for="name">Nombre del Vehículo</label>
              <input type="text" id="name" v-model="newPublication.name" required />
            </div>
            <div class="form-group">
              <label for="price">Precio</label>
              <input type="number" id="price" v-model="newPublication.price" required />
            </div>
            <div class="form-group">
              <label for="status">Estado</label>
              <select id="status" v-model="newPublication.status" required>
                <option value="Nuevo">Nuevo</option>
                <option value="Usado">Usado</option>
              </select>
            </div>
            <div class="form-group">
              <label for="description">Descripción</label>
              <textarea id="description" v-model="newPublication.description" required></textarea>
            </div>
            <button type="submit" class="submit-button">Guardar Publicación</button>
            <button type="button" @click="showForm = false" class="cancel-button">Cancelar</button>
          </form>
        </div>

        <!-- Si showForm es false, mostrar tabla -->
        <div v-else>
          <table class="publication-table">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Editar</th>
                <th>Eliminar</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Publicación 1</td>
                <td>
                  <button class="edit-button">✏️</button>
                </td>
                <td>
                  <button class="delete-button">🗑️</button>
                </td>
              </tr>
              <tr>
                <td>Publicación 2</td>
                <td>
                  <button class="edit-button">✏️</button>
                </td>
                <td>
                  <button class="delete-button">🗑️</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Gestión de Vehículos -->
      <div v-if="selectedSection === 'Gestión de vehiculos'">
        <h2>Gestión de vehículos</h2>

        <!-- Botón para crear nueva publicación -->
        <div class="actions">
          <button class="create-button" @click="showForm = true">Añadir vehiculo</button>
        </div>

        <!-- Contenedor con desplazamiento -->
        <div class="content-scrollable">
          <!-- Si showForm es true, mostrar formulario -->
          <div v-if="showForm" class="form-container">
            <form class="publication-form" @submit.prevent="savePublication">
              <div class="form-group">
                <label for="marca">Marca</label>
                <input type="text" id="marca" v-model="newPublication.marca" required />
              </div>
              <div class="form-group">
                <label for="modelo">Modelo</label>
                <input type="text" id="modelo" v-model="newPublication.modelo" required />
              </div>
              <div class="form-group">
                <label for="anio">Año</label>
                <input type="number" id="anio" v-model="newPublication.anio" required />
              </div>
              <div class="form-group">
                <label for="transmisionTipe">Tipo de transmisión</label>
                <input type="text" id="transmisionTipe" v-model="newPublication.transmisionTipe" required />
              </div>
              <div class="form-group">
                <label for="categoria">Categoría</label>
                <input type="text" id="categoria" v-model="newPublication.categoria" required />
              </div>
              <div class="form-group">
                <label for="BodyType">Tipo de Cuerpo</label>
                <input type="text" id="BodyType" v-model="newPublication.BodyType" required />
              </div>
              <div class="form-group">
                <label for="combustible">Combustible</label>
                <input type="text" id="combustible" v-model="newPublication.combustible" required />
              </div>
              <div class="form-group">
                <label for="disponibilidad">Disponible</label>
                <input type="checkbox" id="disponibilidad" v-model="newPublication.disponibilidad" />
              </div>
              <button type="submit" class="submit-button">Guardar Publicación</button>
              <button type="button" @click="showForm = false" class="cancel-button">Cancelar</button>
            </form>
          </div>

          <!-- Si showForm es false, mostrar tabla -->
          <div v-else>
            <table class="publication-table">
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Editar</th>
                  <th>Eliminar</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(publication, index) in publications" :key="index">
                  <td>{{ publication.name }}</td>
                  <td>
                    <button class="edit-button" @click="editPublication(index)">✏️</button>
                  </td>
                  <td>
                    <button class="delete-button" @click="deletePublication(index)">🗑️</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- Informes -->
      <div v-else-if="selectedSection === 'Informes'">
        <h2>Informes</h2>
        <!-- Tabla de informes -->
        <table class="publication-table">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Descargar</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Informe 1</td>
              <td>
                <button class="Descargar">⬇️</button>
              </td>
            </tr>
            <tr>
              <td>Informe 2</td>
              <td>
                <button class="descarga-button">⬇️</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Boletas -->
      <div v-else-if="selectedSection === 'Boletas'">
        <h2>Acá se mostrará un listado de boletas para visualizar</h2>

        <table class="publication-table">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Descargar</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Boleta 1</td>
              <td>
                <button class="Descargar">⬇️</button>
              </td>
            </tr>
            <tr>
              <td>Boleta 2</td>
              <td>
                <button class="descarga-button">⬇️</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Cerrar Sesion -->
      <div v-else-if="selectedSection === 'Cerrar Sesión'">
        <h2>Sesion cerrada</h2>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: "Administrador",
  data() {
    return {
      menuItems: [
        "Gestión de Publicaciones",
        "Gestión de vehiculos",
        "Informes",
        "Boletas",
        "Cerrar Sesión",
      ],
      selectedSection: "Gestión de Publicaciones",
      showForm: false,
      newPublication: {
        name: '',
        price: '',
        status: 'Nuevo',
        description: '',
        marca: '',
        modelo: '',
        anio: '',
        transmisionTipe: '',
        categoria: '',
        BodyType: '',
        combustible: '',
        disponibilidad: false
      },
      publications: [
        { name: "Vehículo 1" },
        { name: "Vehículo 2" }
      ]
    };
  },
  methods: {
    changeSection(section) {
      this.selectedSection = section;
    },
    savePublication() {
      this.publications.push(this.newPublication);
      this.showForm = false;
    },
    editPublication(index) {
      // Implementa la lógica para editar la publicación
    },
    deletePublication(index) {
      this.publications.splice(index, 1);
    }
  }
};
</script>

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
  color: #333;
  padding: 20px 10px;
  box-shadow: 2px 0 4px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 10px;
}

td {
  color: #333;
  font-size: 1rem;
  padding: 10px;
}

.user-info {
  margin-bottom: 20px;
  font-weight: bold;
  text-align: center;
}

.sidebar nav ul {
  list-style: none;
  padding: 0;
}

.sidebar nav ul li {
  padding: 10px 15px;
  cursor: pointer;
  border-radius: 5px;
  margin-bottom: 5px;
  transition: background 0.3s;
}

.sidebar nav ul li:hover,
.sidebar nav ul li.active {
  background-color: #dcdcdc;
}

/* Diseño Gestion de productos */
.main-content {
  background-color: #fff;
  padding: 20px;
}

.actions {
  margin-bottom: 15px;
}

.create-button {
  background-color: #2196f3;
  color: #f8f8f8;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
  border-radius: 5px;
}

.create-button:hover {
  background-color: #1976d2;
}

/* Formulario */
.form-container {
  max-height: 400px;
  overflow-y: auto;
  padding-right: 10px; /* Espacio para la barra de desplazamiento */
}

.publication-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
  max-width: 500px;
  margin-top: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input,
select,
textarea {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

textarea {
  resize: vertical;
  min-height: 100px;
}

.submit-button {
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #45a049;
}

.cancel-button {
  background-color: #f44336;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.cancel-button:hover {
  background-color: #e53935;
}

/* Tabla */
.publication-table {
  width: 100%;
  border-collapse: collapse;
}

.publication-table th,
.publication-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #000000;
}

.publication-table th {
  background-color: #a3a3a3;
  font-weight: bold;
}

.publication-table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.edit-button,
.delete-button {
  background: none;
  border: none;
  cursor: pointer;
}

.edit-button:hover {
  color: #1976d2;
}

.delete-button:hover {
  color: #f44336;
}

/* Diseño responsive */
@media (max-width: 768px) {
  .container {
    grid-template-columns: 1fr;
    grid-template-rows: 60px 1fr;
  }

  .sidebar {
    display: none;
  }
}
</style>