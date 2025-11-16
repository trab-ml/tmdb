<template>
  <v-container class="mx-3">
    <v-app-bar :elevation="2" rounded>
      <v-app-bar-title>
        <RouterLink to="/">
          <div class="tooltip">
            {{t('header.logoText')}} <span class="tooltiptext"> {{t('header.logoTooltipText')}} </span>
          </div>
        </RouterLink>
      </v-app-bar-title>

      <template v-slot:append>
        <RouterLink to="/">
          {{ t('header.navbar.popularMovies') }}
        </RouterLink>
        <RouterLink to="/top-rated" style="margin-left: 0.5rem;">{{ t('header.navbar.topRated') }}</RouterLink>
        <RouterLink to="/profile" style="margin-left: 0.5rem; margin-right: 1rem">
          {{ t('header.navbar.profile') }}
        </RouterLink>
      </template>
    </v-app-bar>
  </v-container>
</template>

<script setup lang="ts">
import { useI18n } from "vue-i18n"

const {t} = useI18n()
</script>

<style scoped>
.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 14rem;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 1rem;
  margin: 0 0.5rem;

  position: absolute;
  z-index: 1;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
}

a {
  text-decoration: none !important;
}

.router-link-active.router-link-exact-active {
  color: var(--main-color);
  border-bottom: 0.25rem solid;
}

</style>