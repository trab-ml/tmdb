<template>
  <v-app id="custom-v-app">
    <RouterView/>
  </v-app>

  <Footer/>
</template>

<style>
:root {
  --main-color: #46059f;
  box-sizing: border-box;
}

#custom-v-app {
  overflow: hidden;
}

main {
  display: flex;
  flex-direction: column;
  flex-grow: 0;
  flex-shrink: 0;
  margin: 1rem;
}
</style>

<script setup lang="ts">
import Footer from "./components/atoms/Footer.vue"
</script>