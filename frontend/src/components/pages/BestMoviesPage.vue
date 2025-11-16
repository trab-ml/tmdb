<template>
  <Header />
  <main>
    <h1>{{ t('popularMoviesPage.title') }}</h1>

    <form action="" @submit.prevent="handleSubmit">
      <TableOfProfiles v-if="profiles.length > 0" :items="profiles" :formMode="true"
                       @getInputValue="v => selectedProfile = v"/>

      <SubmitButton v-if="profiles.length > 0" :value="t('popularMoviesPage.submitButtonText')"
                    class="search-button"
                    :disabled="selectedProfile === ''"/>
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

const {t} = useI18n()
let movieList = ref()
const moviesService = new MoviesService()
const profilesService = new ProfilesService()
const profiles = ref([])
let selectedProfile = ref('')

onMounted(async () => {
  movieList.value = await moviesService.getMovies(true)
  profiles.value = await profilesService.getProfiles()
})

const handleSubmit = async () => {
  movieList.value = await moviesService.searchByProfile(selectedProfile.value);
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
