<template>
    <v-app id="custom-v-app">
        <RouterView />
    </v-app>

    <Footer />
</template>

<style scoped>
#custom-v-app {
    overflow: hidden;
}
</style>

<script setup lang="ts">
import Footer from "./components/atoms/Footer.vue";
</script>
