<template>
  <div class="movies-container">
    <v-card
        v-for="movie in props.movieList"
        class="mx-auto"
        max-width="344"
    >
      <v-img
          height="200px"
          :src="`https://image.tmdb.org/t/p/w500${movie.coverImgUrl}`"
          cover
          class="cover-img"
      ></v-img>

      <v-card-title>
        {{ movie.title }}
      </v-card-title>

      <v-card-subtitle style="display: flex; justify-content: space-between;">
        <span class="d-inline">{{ $t('popularMoviesPage.moviesContainer.outDate') }}: {{ movie.releaseDate }}</span>
        <v-img v-if="movie.adult === true" :src="atLeastEighteen" alt=""/>
      </v-card-subtitle>

      <v-card-actions>
        <v-btn
            text="Explore"
            class="sexier"
            variant="text"
            :ripple="false"
        ></v-btn>

        <v-spacer></v-spacer>

        <v-btn
            :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
            @click="show = !show"
            class="cursor-pointer sexier"
        ></v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>
            {{ movie.overview }}
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue"
import atLeastEighteen from "/src/assets/-18-32px.png"

let show = ref(false)

const props = defineProps({
  movieList: Array
})
</script>

<style scoped>
.movies-container {
  display: grid;
  grid-template-columns: 18rem 18rem 18rem 18rem;
  background-color: #f2f2f2;
  gap: 1rem;
  padding: 1rem;
  border-radius: 1rem;
}

.movies-container > div {
  border: 1px solid black;
  padding: 10px;
}

.cover-img {
  transition: transform .1s !important;
  cursor: pointer;
}

.cover-img:hover {
  transform: scale(1.1) !important;
}

:deep(.v-btn.sexier) {
  background: none !important;
  color: inherit !important;
}

:deep(.v-btn.sexier:hover),
:deep(.v-btn.sexier:focus), /* A little point of reference doesn't hurt :) */
:deep(.v-btn.sexier:active) {
  color: #f2f2f2 !important;
  background-color: #000 !important;
}

:deep(.v-btn.sexier .v-btn__overlay) {
  opacity: 0 !important;
}
</style>
