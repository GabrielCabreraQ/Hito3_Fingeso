<script setup>
import Home from './vistas/home.vue'; // Importa el componente Admin directamente
</script>

<template>
  <RouterView />
</template>


<style>
/* Estilos generales para hacer la aplicación responsive */
html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  font-size: 16px; /* Base de tamaño de fuente */
  font-family: Arial, sans-serif; /* Fuente legible */
  box-sizing: border-box;
}

#app {
  display: flex;
  flex-direction: column; /* Apila elementos en columna */
  justify-content: center; /* Centra verticalmente */
  align-items: center; /* Centra horizontalmente */
  height: 100%; /* Ocupa toda la altura */
  width: 100%; /* Ocupa toda la anchura */
  padding: 1rem; /* Espaciado interno adaptable */
  background-color: #f9f9f9; /* Fondo claro */
}

/* Asegurarse de que el contenido dentro de Admin se ajuste */
#app > * {
  max-width: 1200px; /* Ancho máximo del contenido */
  width: 100%; /* Ocupa el 100% del ancho disponible */
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* Sombras sutiles */
  border-radius: 8px; /* Bordes redondeados */
  background-color: white; /* Fondo blanco para destacar */
  overflow: hidden; /* Manejar contenido desbordado */
}

/* Ajustes de diseño para pantallas más pequeñas */
@media (max-width: 768px) {
  #app {
    padding: 0.5rem; /* Reduce el padding en pantallas pequeñas */
  }

  #app > * {
    border-radius: 4px; /* Reduce el radio en pantallas pequeñas */
  }
}

/* Ajustes de diseño para pantallas ultra pequeñas */
@media (max-width: 480px) {
  html {
    font-size: 14px; /* Reduce el tamaño de fuente base */
  }

  #app {
    padding: 0.25rem; /* Ajusta aún más el padding */
  }
}
</style>

