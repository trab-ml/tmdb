<script setup lang="ts">
// import SvgIcon from '@jamescoyle/vue-icon';
// import { mdiAccessPoint } from '@mdi/js';
</script>

<template>
  <div>
    <h2>
      Ceci n'est pas un test!
      <v-icon icon="$vuetify"></v-icon>
        <v-icon icon="mdi-access-point"/>
    </h2>

<!--    <svg-icon type="mdi" :path="mdiAccessPoint"></svg-icon>-->

    <v-btn
        class="ma-2"
        color="primary"
    >
      Accept
      <v-icon
          icon="mdi-checkbox-marked-circle"
          end
      ></v-icon>
    </v-btn>

    <v-icon
        color="green-darken-2"
        icon="mdi-domain"
        size="large"
    ></v-icon>
  </div>

  <v-row class="py-4" justify="space-around">
    <v-icon
        color="green-darken-2"
        icon="mdi-domain"
        size="large"
    ></v-icon>

    <v-icon
        color="blue-darken-2"
        icon="mdi-message-text"
        size="large"
    ></v-icon>

    <v-icon
        color="purple-darken-2"
        icon="mdi-dialpad"
        size="large"
    ></v-icon>

    <v-icon
        color="teal-darken-2"
        icon="mdi-email"
        size="large"
    ></v-icon>

    <v-icon
        color="blue-grey-darken-2"
        icon="mdi-call-split"
        size="large"
    ></v-icon>

    <v-icon
        color="orange-darken-2"
        icon="mdi-arrow-up-bold-box-outline"
        size="large"
    ></v-icon>
  </v-row>
</template>
