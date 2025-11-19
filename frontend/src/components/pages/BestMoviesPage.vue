<template>
  <Header />
  <main>
    <h1>{{ t('popularMoviesPage.title') }}</h1>

    <form action="" @submit.prevent="handleSubmit">
      <div v-if="profiles.length > 0" class="d-flex flex-row align-center"
           style="display: flex; border: 1px solid #e0e0e0; border-radius: 1.5rem 1.5rem 0.1rem 0.1rem; border-bottom: none;">
        <TableOfProfiles :items="profiles" :formMode="true"
                         @getInputValue="v => selectedProfile = v"
                         style="width: 75%;"/>
        <div style="width: 25%; display: flex; flex-direction: row; justify-items: center; align-items: center;">
          <SubmitButton :value="t('popularMoviesPage.submitButtonText')"
                        class="search-button"
                        style="margin: 0 auto;"
                        :disabled="selectedProfile === ''"/>
        </div>
      </div>
      <p v-else>
        {{ t('popularMoviesPage.emptyProfileList.preLink') }}
        <RouterLink to="/profile">{{ t('popularMoviesPage.emptyProfileList.link') }}</RouterLink>
        {{ t('popularMoviesPage.emptyProfileList.postLink') }}
      </p>
    </form>

    <MoviesContainer :movieList="movieList" />
  </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue"
import MoviesService from "../../services/MoviesService.ts"
import {onMounted, ref} from "vue"
import TableOfProfiles from "../organisms/TableOfProfiles.vue"
import ProfilesService from "../../services/ProfilesService.ts"
import SubmitButton from "../atoms/SubmitButton.vue"
import MoviesContainer from "../organisms/MoviesContainer.vue"
import {useI18n} from "vue-i18n"
import type {IMovie, IProfile} from "../../types/global.ts"

const {t} = useI18n()
const movieList = ref<IMovie[]>([])
const moviesService = new MoviesService()
const profilesService = new ProfilesService()
const profiles = ref<IProfile[]>([])
const selectedProfile = ref<string>('')

onMounted(async () => {
  movieList.value = await moviesService.getMovies(true)
  profiles.value = await profilesService.getProfiles()
})

const handleSubmit = async () => {
  movieList.value = await moviesService.searchByProfile(selectedProfile.value)
}
</script>

<style scoped>
.search-button {
  display: flex;
  justify-self: center !important;
  margin: 2rem 0;
  border: 1px solid;
  cursor: pointer;
}

.search-button:hover {
  filter: drop-shadow(0 0 0.75rem var(--main-color));
}
</style>
