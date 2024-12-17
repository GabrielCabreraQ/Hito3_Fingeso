<template>
  <div class="container">
    <!-- Barra Superior -->
    <header class="header">
      <h1>Inventario</h1>
    </header>

    <!-- Menú Lateral -->
    <aside class="sidebar">
      <div class="user-info">
        <div class="user-name">EasyWheels</div>
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
          <button class="create-button" @click="showForm = true" @submit.prevent="submitPublication">Nueva publicación</button>
        </div>

        <!-- Si showForm es true, mostrar formulario -->
        <div v-if="showForm">
          <form @submit.prevent="submitPublication">
            <div class="form-group">
              <label for="idVehiculo">ID Vehículo</label>
              <input type="number" id="idVehiculo" v-model="newPublication.vehiculo.idVehiculo" required />
            </div>
            <div class="form-group">
              <label for="precioNormal">Precio Normal</label>
              <input type="number" id="precioNormal" v-model="newPublication.precioNormal" required />
            </div>
            <div class="form-group">
              <label for="codigoACRISS">Código ACRISS</label>
              <input type="text" id="codigoACRISS" v-model="newPublication.codigoACRISS" />
            </div>
            <div class="form-group">
              <label for="visibilidad">Visibilidad</label>
              <input type="checkbox" id="visibilidad" v-model="newPublication.visibilidad" />
            </div>
            <button type="submit" class="submit-button">Guardar Publicación</button>
            <button type="button" @click="showForm = false" class="cancel-button">Cancelar</button>
          </form>
        </div>

        <!-- Si showForm es false, mostrar tabla -->
        <div v-else>
          <div class="scrollable-table-container">
            <div>
              <!-- Tabla de publicaciones -->
              <table v-if="!showForm2" class="publication-table">
                <thead>
                  <tr>
                    <th>Publicación ID</th>
                    <th>Vehículo ID</th>
                    <th>Modelo</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(publication, index) in publications" :key="publication.idPublicacion">
                    <td>{{ publication.idPublicacion }}</td>
                    <td>{{ publication.vehiculo.idVehiculo }}</td>
                    <td>{{ publication.vehiculo.modelo }}</td>
                    <td>
                      <button class="edit-button" @click="editPublication(publication)">✏️</button>
                    </td>
                    <td>
                      <button class="delete-button" @click="deletePublication(publication.idPublicacion)">🗑️</button>
                    </td>
                  </tr>
                </tbody>
              </table>

              <!-- Formulario de edición -->
              <div v-if="showForm2" class="edit-form">
                <h3>Editar Publicación</h3>
                <form @submit.prevent="updatePublication">
                  <label for="vehiculoId">ID Vehículo:</label>
                  <input type="text" :value="currentPublication.vehiculo.idVehiculo" disabled />

                  <label for="precioNormal">Precio Normal:</label>
                  <input type="number" v-model="currentPublication.precioNormal" required />

                  <label for="codigoACRISS">Código ACRISS:</label>
                  <input type="text" v-model="currentPublication.codigoACRISS" />

                  <label for="visibilidad">Visibilidad:</label>
                  <input type="checkbox" v-model="currentPublication.visibilidad" />

                  <button type="submit">{{ isEditing ? 'Confirmar cambios' : 'Guardar publicación' }}</button>
                  <button type="button" @click="cancelEdit">Cancelar</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Gestión de Vehículos -->
      <div v-if="selectedSection === 'Gestión de Vehiculos'">
        <h2>Gestión de Vehículos</h2>

        <!-- Botón para crear un nuevo vehículo -->
        <div class="actions">
        <button class="create-button" @click="showForm = true">Añadir vehículo</button>
        </div>

        <!-- Botón para crear un nuevo vehículo -->
        <div class="actions">
        <button class="create-button" @click="showForm3 = true">Gestionar Devolucion</button>
        </div>

        <!-- Mostrar formulario si showForm es true -->
        <div v-if="showForm">
        <div class="scrollable-table-container">
          <form @submit.prevent="addVehiculo">
          <div class="form-group">
            <label for="marca">Marca</label>
            <input type="text" id="marca" v-model="newVehiculo.marca" required />
          </div>
          <div class="form-group">
            <label for="modelo">Modelo</label>
            <input type="text" id="modelo" v-model="newVehiculo.modelo" required />
          </div>
          <div class="form-group">
            <label for="anio">Año</label>
            <input type="number" id="anio" v-model="newVehiculo.anio" required />
          </div>
          <div class="form-group">
            <label for="transmisionTipe">Tipo de transmisión</label>
            <input type="text" id="transmisionTipe" v-model="newVehiculo.tipoDeTransmision" required />
          </div>
          <div class="form-group">
            <label for="categoria">Categoría</label>
            <input type="text" id="categoria" v-model="newVehiculo.categoria" required />
          </div>
          <div class="form-group">
            <label for="BodyType">Tipo de Cuerpo</label>
            <input type="text" id="BodyType" v-model="newVehiculo.tipoDeCuerpo" required />
          </div>
          <div class="form-group">
            <label for="combustible">Combustible</label>
            <input type="text" id="combustible" v-model="newVehiculo.combustibleAC" required />
          </div>
          <div class="form-group">
            <label for="disponibilidad">Disponible para arrendar</label>
            <input type="checkbox" id="disponibilidad" v-model="newVehiculo.disponible_uso" />
          </div>
          <button type="submit" class="submit-button">Guardar Vehículo</button>
          <button type="button" @click="cancelEdit" class="cancel-button">Cancelar</button>
        </form>
        </div>
        </div>

        <div v-if="showForm3">
          <form @submit.prevent="gestionarDevolucion">
            <div class = "form-group">
              <label for="disponibilidad">ID del Vehiculo</label>
              <input type="number" id="id_vehiculo" v-model="newVehiculo.id_vehiculo" />
            </div>
            <div class="form-group">
              <label for="disponibilidad">Disponible para arrendar</label>
              <input type="checkbox" id="disponibilidad" v-model="newVehiculo.disponible_uso" />
            </div>
            <button type="submit" class="submit-button">Guardar Cambios</button>
            <button type="button" @click="cancelEdit" class="cancel-button">Cancelar</button>
          </form>
        </div>

        <div v-if="isEditVehiculo" class="edit-form">
          <h3>Editar Vehículo</h3>
          <form @submit.prevent="updateVehiculo">
            <label for="id_vehiculo">Id Vehiculo:</label>
            <input type="text" id="id_vehiculo" v-model="currentVehiculo.id_vehiculo" required />

            <label for="marca">Marca:</label>
            <input type="text" id="marca" v-model="currentVehiculo.marca" required />

            <label for="modelo">Modelo:</label>
            <input type="text" id="modelo" v-model="currentVehiculo.modelo" required />

            <label for="anio">Año:</label>
            <input type="number" id="anio" v-model="currentVehiculo.anio" required />

            <label for="transmision">Tipo de Transmisión:</label>
            <input type="text" id="transmision" v-model="currentVehiculo.tipoDeTransmision" required />

            <label for="categoria">Categoría:</label>
            <input type="text" id="categoria" v-model="currentVehiculo.categoria" required />

            <label for="tipoCuerpo">Tipo de Cuerpo:</label>
            <input type="text" id="tipoCuerpo" v-model="currentVehiculo.tipo_cuerpo" required />

            <label for="combustible">Combustible:</label>
            <input type="text" id="combustible" v-model="currentVehiculo.combustibleAC" required />

            <label for="disponibilidad">Disponible para arrendar:</label>
            <input type="checkbox" id="disponibilidad" v-model="currentVehiculo.disponible_uso" />

            <button type="submit">Confirmar cambios</button>
            <button type="button" @click="cancelEdit" class="cancel-button">Cancelar</button>
          </form>
        </div>
        

        <!-- Mostrar tabla de vehículos si showForm es false -->
        <div v-if =" !showForm && !showForm3 && !isEditVehiculo">
          <div class="scrollable-table-container">
            <table class="vehiculo-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Marca</th>
                  <th>Modelo</th>
                  <th>Año</th>
                  <th>Transmisión</th>
                  <th>Categoría</th>
                  <th>Combustible</th>
                  <th>Disponible Para Arrendar</th>
                  <th>Modificar Vehiculo</th>
                  <th>Eliminar Vehiculo</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(vehiculo, index) in vehiculos" :key="vehiculo.idVehiculo">
                  <td>{{ vehiculo.idVehiculo }}</td>
                  <td>{{ vehiculo.marca }}</td>
                  <td>{{ vehiculo.modelo }}</td>
                  <td>{{ vehiculo.anio }}</td>
                  <td>{{ vehiculo.tipoTransmision }}</td>
                  <td>{{ vehiculo.categoria }}</td>
                  <td>{{ vehiculo.combustibleAC }}</td>
                  <td>{{ vehiculo.disponible_uso ? 'Sí' : 'No' }}</td>
                  <td>
                    <button class="edit-button" @click="editVehiculo(vehiculo)">✏️</button>
                  </td>
                  <td>
                    <button class="delete-button" @click="deleteVehiculo(vehiculo.idVehiculo)">🗑️</button>
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
        <div class="actions">
          <button @click="cerrarSesion" class="menu-button">Cerrar Sesión</button>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axios from "axios";

function direccionamientoMain(){
  window.location.href = '/home'
}

export default {
  name: "Administrador",
  data() {
    return {
      menuItems: [
        "Gestión de Publicaciones",
        "Gestión de Vehiculos", 
        "Informes",
        "Boletas",
        "Cerrar Sesión",
      ],
      selectedSection: "Gestión de Publicaciones",
      showForm: false,
      newVehiculo: {
        marca: '',
        modelo: '',
        anio: '',
        tipoDeTransmision: '',
        categoria: '',
        tipoDeCuerpo: '',
        combustibleAC: '',
        disponibilidad: [
            "2024-12-24",
            "2024-12-28",
            "2025-01-03"
        ],
        disponible_uso: false
      },
      newPublication: {
        vehiculo: {
          idVehiculo: '', // ID del vehículo
        },
        precioNormal: '', // Precio normal
        codigoACRISS: '', // Código ACRISS
        visibilidad: true, // Visibilidad
        },

        currentVehiculo: {
          id_vehiculo: '',
          marca: '',
          modelo: '',
          anio: null,
          tipoDeTransmision: '',
          categoria: '',
          tipo_cuerpo: '',
          combustibleAC: '',
          disponible_uso: false
        },

        vehiculos: [], // Lista de vehículos
        
        publications: [], // Lista de publicaciones
        isEditing: false, // Controla si el formulario de edición está visible
        editedPublication: {}, // Datos de la publicación que estamos editando
        showForm2: false, // Controla si el formulario de edición está visible
        showForm3: false,
        isEditVehiculo: false, //Controla si se esta o no editando un vehiculo
    };
  },
  methods: {
    changeSection(section) {
      this.selectedSection = section;
      if (section === "Gestión de Publicaciones" && !this.showForm) {
        this.fetchPublications();  // Llamamos al método para cargar las publicaciones
      }
      if(section === "Gestión de Vehiculos" && !this.showForm){
        this.fetchVehiculos();
      }
    },
    async cerrarSesion(){
      // Limpiar la sesión (si es necesario)
      localStorage.removeItem('login');
      direccionamientoMain();
    },
    savePublication() {
      this.publications.push(this.newPublication);
      this.showForm = false;
    },
    // Método para cancelar la edición y cerrar el formulario
    cancelEdit() {
      this.isEditing = false;
      this.showForm = false;
      this.showForm2 = false;
      this.showForm3 = false;
      this.isEditVehiculo = false;
    },

    editPublication(publication) {
      this.isEditing = true; // Activar el modo de edición
      this.currentPublication = { ...publication }; // Copiar los datos de la publicación seleccionada
      this.showForm2 = true; // Mostrar el formulario de edición
    },
    editVehiculo(vehiculo) {
      this.isEditVehiculo = true; // Activar el modo de edición
      this.currentVehiculo = { ...vehiculo }; // Copiar los datos de la publicación seleccionada
    },

    // Método para editar una publicación
    async updatePublication() {
      if (!this.currentPublication || !this.currentPublication.vehiculo) {
        console.error("La publicación o el vehículo no están definidos.");
        return;
      }

      const publicationToUpdate = {
        vehiculo: this.currentPublication.vehiculo,
        precioNormal: this.currentPublication.precioNormal,
        codigoACRISS: this.currentPublication.codigoACRISS,
        visibilidad: this.currentPublication.visibilidad
      };

      try {
        const response = await axios.put(
          `${import.meta.env.VITE_BASE_URL}administradores/${this.currentPublication.idPublicacion}/updatePublicacion`,
          publicationToUpdate
        );
        console.log('Publicación actualizada:', response.data);
        this.fetchPublications();
        this.isEditing = false;
        this.showForm2 = false;
      } catch (error) {
        console.error('Error al actualizar la publicación:', error);
        alert('No se pudo actualizar la publicación. Verifica tu conexión.');
      }
    },
    async deletePublication(idPublicacion) {
      if (!confirm("¿Estás seguro de que deseas eliminar esta publicación?")) {
        return; // Cancelar si el usuario no confirma
      }

      try {
        const response = await axios.delete(
          `${import.meta.env.VITE_BASE_URL}administradores/deletePublicacion/${idPublicacion}`
        );
        console.log("Publicación eliminada:", response.data);

        // Actualiza la lista de publicaciones
        this.fetchPublications();
      } catch (error) {
        alert("Publicación eliminada exitosamente.");
      }
    },

    async deleteVehiculo(idVehiculo){
      if (!confirm("¿Estás seguro de que deseas eliminar este vehiculo?")) {
        return; // Cancelar si el usuario no confirma
      }
      try{
        const response = await axios.delete(
          `${import.meta.env.VITE_BASE_URL}vehiculos/${idVehiculo}?permiso=administrador`
        );
        console.log("Publicación eliminada:", response.data);
        alert("Vehiculo eliminado correctamente.")
        // Actualiza la lista de publicaciones
        this.fetchVehiculos();
      }catch(error){
        alert("No se pudo eliminar vehiculo. Posiblemente este asociado a un arriendo.");
      }
    },

    submitPublication() {
      // Verificar si todos los campos están completos
      if (
        !this.newPublication.vehiculo.idVehiculo ||
        !this.newPublication.precioNormal
      ) {
        alert('Por favor, completa todos los campos.');
        return;
      }

      // Enviar datos al backend
      axios.post(
        import.meta.env.VITE_BASE_URL + "publicaciones?permiso=administrador",
        this.newPublication
      )
      .then(response => {
        console.log('Publicación guardada:', response.data);
      })
      .catch(error => {
        if (error.response) {
          console.error('Error en la respuesta del servidor:', error.response.data);
          console.error('Código de estado:', error.response.status);
          alert(`Error del servidor: ${error.response.data.message || 'Consulta fallida'}`);
        } else if (error.request) {
          console.error('No se recibió respuesta del servidor:', error.request);
          alert('No se pudo conectar con el servidor. Verifica tu conexión.');
        } else {
          console.error('Error al configurar la solicitud:', error.message);
          alert('Ocurrió un error inesperado. Intenta nuevamente.');
        }
      });
      this.showForm = false;
    },

    fetchPublications() {
      axios.get(import.meta.env.VITE_BASE_URL + "publicaciones")
        .then(response => {
          console.log("Publicaciones obtenidas:", response.data);
          this.publications = response.data; // Asignar las publicaciones al arreglo
        })
        .catch(error => {
          console.error("Error al obtener las publicaciones:", error);
          alert("No se pudo obtener la lista de publicaciones. Verifica tu conexión.");
        });
    },
    async fetchVehiculos() {
    try {
      const response = await axios.get(import.meta.env.VITE_BASE_URL + "vehiculos?permiso=administrador");
      console.log("Vehículos obtenidos:", response.data);
      this.vehiculos = response.data;
    } catch (error) {
      console.error("Error al obtener los vehículos:", error);
      alert("No se pudo obtener la lista de vehículos. Verifica tu conexión.");
    }
  },
    async addVehiculo(){
      const vehiculo = {
        marca: this.newVehiculo.marca,
        modelo: this.newVehiculo.modelo,
        anio: this.newVehiculo.anio,
        tipoTransmision: this.newVehiculo.tipoDeTransmision,
        categoria: this.newVehiculo.categoria,
        tipo_cuerpo: this.newVehiculo.tipoDeCuerpo,
        combustibleAC: this.newVehiculo.combustibleAC,
        disponibilidad: [
            "2024-12-24",
            "2024-12-28",
            "2025-01-03"
        ],
        devuelto: true,
        disponible_uso: this.newVehiculo.disponible_uso
      };
      try{
        const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "administradores/createVehiculo", vehiculo);
        alert("Vehiculo creado correctamente.");
        this.showForm = false;
        // Limpiar los campos del formulario
        this.newVehiculo = {
            marca: '',
            modelo: '',
            anio: null,
            tipoDeTransmision: '',
            categoria: '',
            tipoDeCuerpo: '',
            combustibleAC: '',
            disponible_uso: false
        };
      } catch(error){
        console.error('Error al registrar el vehículo:', error);
        alert('Hubo un problema al registrar el vehículo. Inténtalo nuevamente.');
      }
    },
    async gestionarDevolucion(){
      if (
        !this.newVehiculo.id_vehiculo
      ) {
        alert('Por favor, ingresa el ID del vehiculo.');
        return;
      }
      if(this.newVehiculo.disponible_uso){
        try{
          const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "vehiculos/" + 
          this.newVehiculo.id_vehiculo +"/devolucion?observaciones=No tiene fallas");
          alert("Gestion del Vehiculo exitosa.");
          this.newVehiculo.id_vehiculo = '',
          this.newVehiculo.disponible_uso = false;
          this.showForm3 = false;
        }catch(error){
          console.error('Error al gestionar la devolucion del vehiculo:', error);
          alert('Hubo un problema al gestionar la devolucion del vehículo en disponible. Inténtalo nuevamente.');
        }
      }else{
        try{
          const respuesta = await axios.post(import.meta.env.VITE_BASE_URL + "vehiculos/" + 
          this.newVehiculo.id_vehiculo +"/devolucion?observaciones=Presenta fallas");
          alert("Gestion del Vehiculo exitosa.");
          this.newVehiculo.id_vehiculo = '',
          this.newVehiculo.disponible_uso = false
        }catch(error){
          console.error('Error al gestionar la devolucion del vehiculo:', error);
          alert('Hubo un problema al gestionar la devolucion del vehículo en disponible. Inténtalo nuevamente.');
        }
      }
    },

    async updateVehiculo() {
      // Verificamos si el vehículo actual está definido
      if (!this.currentVehiculo) {
        console.error("El vehículo no está definido.");
        return;
      }

      const vehiculoToUpdate = {
        id_vehiculo: this.currentVehiculo.id_vehiculo,
        marca: this.currentVehiculo.marca,
        modelo: this.currentVehiculo.modelo,
        anio: this.currentVehiculo.anio,
        tipoTransmision: this.currentVehiculo.tipoDeTransmision,
        categoria: this.currentVehiculo.categoria,
        tipoCuerpo: this.currentVehiculo.tipoDeCuerpo,
        combustibleAC: this.currentVehiculo.combustibleAC,
        disponibilidad: [
            "2024-12-24",
            "2024-12-28",
            "2025-01-03"
        ],
        devuelto: this.currentVehiculo.devuelto,
        disponible_uso: this.currentVehiculo.disponible_uso
      };

      // Intentamos hacer la solicitud PUT para actualizar el vehículo
      try {
        const response = await axios.put(
          `${import.meta.env.VITE_BASE_URL}vehiculos/${this.currentVehiculo.id_vehiculo}?permiso=administrador`,
          vehiculoToUpdate
        );
        console.log('Vehículo actualizado:', response.data);
        // Opcional: Llamamos a un método para actualizar la lista de vehículos o hacer algo con la respuesta
        this.fetchVehiculos(); // Suponiendo que tienes un método fetchVehiculos para obtener la lista actualizada
        this.isEditing = false; // Si usas un flag para controlar el estado de edición
        this.showForm = false;  // Si tienes un formulario para ocultar después de la actualización
        this.isEditVehiculo = false;
      } catch (error) {
        console.error('Error al actualizar el vehículo:', error);
        alert('No se pudo actualizar el vehículo. Verifica tu conexión.');
      }
    }

  },
  created() {
  this.fetchPublications();
  this.fetchVehiculos();
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

.scrollable-table-container {
  max-height: 400px; /* Ajusta el tamaño máximo según sea necesario */
  overflow-y: auto;  /* Permite desplazarse verticalmente */
  margin-top: 10px;
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

.vehiculo-table th,
.vehiculo-table td {
  padding: 10px;
  text-align: left;
  border-bottom: 1px solid #000000;
}

.vehiculo-table th {
  background-color: #a3a3a3;
  font-weight: bold;
}

.vehiculo-table tr:nth-child(even) {
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

/* Estilos para el contenedor del formulario */
.edit-form {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Estilo para el título */
.edit-form h3 {
  font-size: 1.5rem;
  margin-bottom: 20px;
  color: #333;
}

/* Estilos para los labels */
.edit-form label {
  display: block;
  font-size: 1rem;
  margin-bottom: 5px;
  color: #666;
}

/* Estilos para los inputs */
.edit-form input[type="text"],
.edit-form input[type="number"],
.edit-form input[type="checkbox"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 15px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  background-color: #fff;
}

/* Estilo para el input de checkbox */
.edit-form input[type="checkbox"] {
  width: auto;
  margin-right: 10px;
}

/* Botones */
.edit-form button {
  padding: 10px 20px;
  font-size: 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
  transition: background-color 0.3s ease;
}

/* Estilo para el botón de guardar/confirmar */
.edit-form button[type="submit"] {
  background-color: #4CAF50;
  color: white;
}

.edit-form button[type="submit"]:hover {
  background-color: #45a049;
}

/* Estilo para el botón de cancelar */
.edit-form button[type="button"] {
  background-color: #f44336;
  color: white;
}

.edit-form button[type="button"]:hover {
  background-color: #e53935;
}

/* Asegurarse de que el formulario esté alineado correctamente */
.edit-form form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.edit-form form input,
.edit-form form button {
  width: 100%;
}

/* Estilos generales para el formulario */
form {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* Estilo para cada grupo de campo */
.form-group {
  margin-bottom: 15px;
}

/* Estilos para las etiquetas */
form label {
  display: block;
  font-size: 1rem;
  margin-bottom: 5px;
  color: #666;
}

/* Estilos para los inputs */
form input[type="text"],
form input[type="number"],
form input[type="checkbox"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
  background-color: #fff;
}

/* Estilo para el input de checkbox */
form input[type="checkbox"] {
  width: auto;
  margin-right: 10px;
}

/* Botón de enviar */
.submit-button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #45a049;
}

/* Botón de cancelar */
.cancel-button {
  padding: 10px 20px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-left: 10px;
}

.cancel-button:hover {
  background-color: #e53935;
}

.edit-form {
  max-height: 400px; /* Altura máxima del formulario */
  overflow-y: auto; /* Scroll vertical si el contenido excede la altura */
  padding: 20px; /* Espacio interno */
  border: 1px solid #ccc; /* Borde para darle estilo */
  border-radius: 8px; /* Bordes redondeados */
  background-color: #f9f9f9; /* Fondo suave */
}

.edit-form h3 {
  text-align: center; /* Centrar el título */
  margin-bottom: 15px;
}

.edit-form form {
  display: flex;
  flex-direction: column; /* Campos en columna */
}

.edit-form label {
  margin-bottom: 5px;
  font-weight: bold;
}

.edit-form input {
  margin-bottom: 15px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}

.edit-form input[type="checkbox"] {
  width: auto; /* Ajustar tamaño del checkbox */
  margin-bottom: 15px;
}

.edit-form button {
  padding: 10px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
  color: white;
}

.edit-form button[type="submit"] {
  background-color: #4CAF50; /* Botón de confirmación verde */
}

.edit-form .cancel-button {
  background-color: #f44336; /* Botón de cancelar rojo */
  margin-top: 10px;
}

.edit-form button:hover {
  opacity: 0.9;
}
</style>