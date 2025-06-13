<template>
  <Header />
  <main>
    <h1>Best Movies</h1>

    <div class="movies-container">
      <v-card
          v-for="movie in movieList"
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

        <v-card-subtitle>
          sortie: {{ movie.releaseDate }}
        </v-card-subtitle>

        <v-card-actions>
          <v-btn
              color="orange-lighten-2"
              text="Explore"
          ></v-btn>

          <v-spacer></v-spacer>

          <v-btn
              :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
              @click="show = !show"
              class="cursor-pointer"
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
  </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue"
import MoviesService from "../services/MoviesService.ts"
import {onMounted, ref} from "vue"

let show = ref(false)
let movieList = ref()
const moviesService = new MoviesService()

onMounted(async () => {
  movieList.value = await moviesService.getPopularMovies()
})

</script>

<style scoped>
.movies-container {
  display: grid;
  grid-template-columns: auto auto auto;
  background-color: #f2f2f2;
  gap: 1rem;
  padding: 1rem;
  border-radius: 1rem;
}
.movies-container > div {
  border: 1px solid black;
  padding: 10px;
}
.cursor-pointer:hover {
  cursor: pointer;
  background: #D3D3D3 !important;
}
.cover-img{
  transition: transform .1s !important;
  cursor: pointer;
}
.cover-img:hover{
  transform: scale(1.1) !important;
}
</style>
