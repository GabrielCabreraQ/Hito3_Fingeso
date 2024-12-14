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
              <tr>
                <td>Publicación 3</td>
                <td>
                  <button class="edit-button">✏️</button>
                </td>
                <td>
                  <button class="delete-button">🗑️</button>
                </td>
              </tr>
              <tr>
                <td>Publicación 4</td>
                <td>
                  <button class="edit-button">✏️</button>
                </td>
                <td>
                  <button class="delete-button">🗑️</button>
                </td>
              </tr>
              <tr>
                <td>Publicación 5</td>
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
            <tr>
              <td>Informe 3</td>
              <td>
                <button class="descarga-button">⬇️</button>
              </td>
            </tr>
            <tr>
              <td>Informe 4</td>
              <td>
                <button class="descarga-button">⬇️</button>
              </td>
            </tr>
            <tr>
              <td>Informe 5</td>
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
      </div>

      <!-- Cerrar Sesion -->
      <div v-else-if="selectedSection === 'Cerrar Sesión'">
        <h2>Sesion cerrada</h2>
      </div>

      <!-- Vista por Defecto -->
      <div v-else>
        <h2>Bienvenido</h2>
        <p>Seleccione una opción del menú.</p>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: "Administrador",
  data() {
    return {
      // Opciones del menú
      menuItems: [
        "Gestión de Publicaciones",
        "Informes",
        "Boletas",
        "Cerrar Sesión",
      ],
      // Sección seleccionada
      selectedSection: "Gestión de Publicaciones",
      // Control de formulario
      showForm: false,
      newPublication: {
        name: '',
        price: '',
        status: 'Nuevo',
        description: ''
      }
    };
  },
  methods: {
    // Cambiar la sección principal
    changeSection(section) {
      this.selectedSection = section;
    },
  },
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

.publication-table tr:hover {
  background-color: #707070;
}

.edit-button,
.delete-button {
  background-color: transparent;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
}

.edit-button {
  color: #2196f3;
}

.delete-button {
  color: #f44336;
}

.descarga-button {
  color: #2196f3;
}
</style>
