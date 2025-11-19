<template>
  <div v-if="normalizedMovies.length > 0" class="movies-container">
    <v-card
        v-for="movie in normalizedMovies"
        :key="movie._uniqueId"
        class="mx-auto"
        max-width="344"
    >
      <v-img
          height="186px"
          :src="movie.coverImgUrl
          ? `https://image.tmdb.org/t/p/w500${movie.coverImgUrl}`
          : defaultCoverImg"
          alt="Movie Cover"
          cover
          class="cover-img"
      ></v-img>

      <v-card-title>
        {{ movie.title }}
      </v-card-title>

      <v-card-subtitle style="display: flex; justify-content: space-between;">
        <span class="d-inline">{{ t('popularMoviesPage.moviesContainer.outDate') }}: {{ movie.releaseDate }}</span>
        <v-img v-if="movie.adult === true" :src="atLeastEighteen" alt=""/>
      </v-card-subtitle>

      <v-card-actions>
        <v-btn
            text
            class="sexier"
            variant="text"
            :ripple="false"
            style="cursor: default"
        >{{ t('popularMoviesPage.moviesContainer.explore') }}</v-btn>

        <v-spacer></v-spacer>

        <v-btn
            :icon="isExpanded(movie) ? 'mdi-chevron-up' : 'mdi-chevron-down'"
            @click="toggleExpand(movie)"
            class="cursor-pointer sexier"
        ></v-btn>
      </v-card-actions>

      <v-expand-transition v-if="isExpanded(movie)">
        <div>
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
import atLeastEighteen from "/src/assets/-18-32px.png"
import defaultCoverImg from "/src/assets/best-movie-slogan.png"
import {useI18n} from "vue-i18n"
import {ref, watch} from "vue"
import type {IMovie} from "../../types/global.ts"

const props = defineProps<{ movieList: IMovie[] }>()
const { t } = useI18n()

const normalizedMovies = ref<IMovie[]>([])
const expandedMap = ref<Map<string, boolean>>(new Map())

function isExpanded(movie: IMovie): boolean {
  return !!expandedMap.value.get(movie._uniqueId!) || false
}

function toggleExpand(movie: IMovie): void {
  const id = movie._uniqueId!
  const currentState = expandedMap.value.get(id) || false

  expandedMap.value.clear()
  expandedMap.value.set(id, !currentState)
}

watch(() => props.movieList, (newList) => {
  if (newList && newList.length > 0) {
    normalizedMovies.value = newList.map((movie) => ({
      ...movie,
      _uniqueId: crypto.randomUUID()
    }))
  }
})
</script>

<style scoped>
.movies-container {
  display: grid;
  grid-template-columns: 18rem 18rem 18rem 18rem;
  grid-auto-rows: min-content;
  align-items: start;
  background-color: #f2f2f2;
  gap: 1rem;
  margin: 0 0 1rem;
  padding: 1rem;
  border-radius: 0 0 1rem 1rem;
  border-top: none;
}

.movies-container > div {
  align-self: start;
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
