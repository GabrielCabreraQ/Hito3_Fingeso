<template>
  <div class="home-container">
    <!-- Barra de menú -->
    <div class="menu-bar">
      <div class="logo">Registro de Arrendatarios</div>
        <button @click="goTologin" class="menu-button">Iniciar sesion</button>
    </div>

    <!-- Contenedor principal -->
    <div class="content-container">
      <div class="content-box">
        <div class="header-content">
          <h1>Registrar Nuevo Arrendatario</h1>
          <p>Por favor, complete la información a continuación:</p>
        </div>
        <!-- Formulario desplazable -->
        <div class="form-scrollable">
          <form class="form-container" @submit.prevent="crearPublicacion">
            <div>
              <label>Nombre:</label>
              <input
                type="text"
                v-model="newPublication.nombreUsuario"
                class="input-field"
                placeholder="Ingrese su nombre"
                required
              />
            </div>
            <div>
              <label>Correo Electrónico:</label>
              <input
                type="email"
                v-model="newPublication.correoUsuario"
                class="input-field"
                placeholder="Ingrese su correo electrónico"
                required
              />
            </div>
            <div>
              <label>Contraseña:</label>
              <input
                type="password"
                v-model="newPublication.contraseniaUsuario"
                class="input-field"
                placeholder="Ingrese su contraseña"
                required
              />
            </div>
            <div>
              <label>Teléfono:</label>
              <input
                type="text"
                v-model="newPublication.telefonoUsuario"
                class="input-field"
                placeholder="Ingrese su número telefónico"
                required
              />
            </div>
            <div>
              <label>Fecha de Nacimiento:</label>
              <input
                type="date"
                v-model="newPublication.fechaNacimiento"
                class="input-field"
                required
              />
            </div>
            <div>
              <label>Tipo de Licencia:</label>
              <input
                type="text"
                v-model="newPublication.tipoLicenciaInput"
                class="input-field"
                placeholder="Ingrese el tipo de licencia (separadas por comas)"
                @input="convertirLicenciaEnArray"
              />
            </div>
            <div>
              <label>Documentos:</label>
              <div class="date-container">
                <input
                  type="text"
                  v-model="nuevoDocumento"
                  class="input-field"
                  placeholder="Añadir documento"
                />
                <button
                  type="button"
                  class="action-button"
                  @click="agregarDocumento"
                >
                  Agregar Documento
                </button>
              </div>
            </div>
            <ul>
              <li v-for="(doc, index) in newPublication.documentos" :key="index">
                {{ doc }}
                <button
                  type="button"
                  class="action-button"
                  @click="eliminarDocumento(index)"
                >
                  Eliminar
                </button>
              </li>
            </ul>
            <button type="submit" class="action-button">Registrar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

function direccionamientologin(){
  window.location.href = '/login'
}

export default {
  data() {
    return {
      newPublication: {
        tipo_usuario: "Arrendatario",
        nombreUsuario: "",
        correoUsuario: "",
        contraseniaUsuario: "",
        telefonoUsuario: "",
        fechaNacimiento: "",
        tipoLicencia: [],
        documentos: [],
      },
      nuevoDocumento: "", // Campo temporal para añadir documentos
      tipoLicenciaInput: "", // Valor del campo de entrada para tipos de licencia
    };
  },
  methods: {
    // Convierte la entrada de tipoLicencia a un array de cadenas
    convertirLicenciaEnArray() {
      this.newPublication.tipoLicencia = this.tipoLicenciaInput
        .split(",")
        .map((item) => item.trim())
        .filter((item) => item !== "");
    },

    async crearPublicacion() {
      // Validar campos requeridos antes de enviar
      if (
        this.newPublication.nombreUsuario &&
        this.newPublication.correoUsuario &&
        this.newPublication.contraseniaUsuario &&
        this.newPublication.telefonoUsuario &&
        this.newPublication.fechaNacimiento &&
        this.newPublication.tipoLicencia
      ) {
        try {
          const response = await axios.post(
            import.meta.env.VITE_BASE_URL + "arrendatarios/register",
            this.newPublication
          );
          
          // Verificar si la respuesta es null
          if (response.data === null) {
            alert("Este correo electrónico ya está registrado.");
          } else {
            console.log("Publicación creada con éxito:", response.data);
            alert("Registro exitoso");
            this.goTologin();
          }
        } catch (error) {
          console.error("Error al crear la publicación:", error);
        }
      } else {
        alert("Por favor, complete todos los campos obligatorios");
      }
    },
    agregarDocumento() {
      if (this.nuevoDocumento.trim()) {
        this.newPublication.documentos.push(this.nuevoDocumento.trim());
        this.nuevoDocumento = ""; // Limpiar el campo
      }
    },
    eliminarDocumento(index) {
      this.newPublication.documentos.splice(index, 1);
    },

    async goTologin(){
      direccionamientologin();
    },
  },
};
</script>

<style scoped>
/* Ajustes generales */
body,
html {
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

.content-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 100px;
  margin-top: 20px; /* Espacio para el menú fijo */
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

/* Formulario desplazable */
.form-scrollable {
  max-height: 400px;
  overflow-y: auto;
  overflow-x: hidden;
  padding-right: 10px; /* Espacio para el scroll */
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

.form-container {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.input-field {
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  width: 100%;
}

.date-container {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 10px;
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
