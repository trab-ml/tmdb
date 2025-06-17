<template>
  <Header />
  <main>
    <h1>{{ $t('popularMoviesPage.title') }}</h1>

    <form action="" @submit.prevent="handleSubmit">
      <TableOfProfiles v-if="profiles.length > 0" :items="profiles" :formMode="true"
                       @getInputValue="v => selectedProfile = v"/>

      <SubmitButton v-if="profiles.length > 0" :value="$t('popularMoviesPage.submitButtonText')"
                    class="cursor-pointer"
                    :disabled="selectedProfile === ''"
                    style="display: flex; justify-self: center !important; margin: 2rem 0"/>
      <p v-else>
        {{ $t('popularMoviesPage.emptyProfileList.preLink') }}
        <RouterLink to="/profile">{{ $t('popularMoviesPage.emptyProfileList.link') }}</RouterLink>
        {{ $t('popularMoviesPage.emptyProfileList.postLink') }}
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
import MoviesContainer from "../organisms/MoviesContainer.vue";

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
.cursor-pointer:hover {
  cursor: pointer;
  background: #D3D3D3 !important;
}
</style>
