<template>
  <Header />
  <main>
    <h1>{{ t('topRatedPage.title') }}</h1>
    <MoviesContainer :movieList="movieList" />
  </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue"
import MoviesService from "../../services/MoviesService.ts"
import {onMounted, ref} from "vue"
import MoviesContainer from "../organisms/MoviesContainer.vue"
import {useI18n} from "vue-i18n"

const {t} = useI18n()
let movieList = ref([])
const moviesService = new MoviesService()

onMounted(async () => {
  movieList.value = await moviesService.getMovies(false)
})
</script>