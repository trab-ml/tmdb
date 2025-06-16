<template>
  <Header />
  <main style="width: 100%; position: relative; display: flex; flex-direction: column; justify-items: center; align-items: center">
    <h1>{{$t('profilePage.title')}}</h1>

    <h2>{{$t('profilePage.formTitle')}}</h2>
    <form
        action=""
        @submit="handleSubmit"
        style="border: 1px solid; border-radius: 1rem; padding: 1rem 0.5rem">
      <CheckboxField :label="$t('profilePage.adultCheckboxLabel')" @getInputValue="(v: boolean) => toSubmit.isAdult = v"/>

      <v-app style="max-height: 6rem !important; padding: 0 !important; margin: 0 !important;" class="d-flex custom-v-app">
        <v-container style="padding: 0 !important; margin: 0 !important;">
          <v-select
              clearable
              chips
              label="Genres"
              :items="Object.values(MovieGenre)"
              multiple
              variant="outlined"
              v-model="desiredGenres"
              class="custom-v-select"
              style="min-width: 8rem;"
          ></v-select>
        </v-container>
      </v-app>

      <SubmitButton class="mt-3" :value="$t('profilePage.submitButtonText')"/>
    </form>

    <TableOfProfiles v-if="profiles.length > 0" :items="profiles" />
    <p v-else>{{$t('profilePage.noSavedGenre')}}</p>
  </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue"
import CheckboxField from "../atoms/CheckboxField.vue"
import {MovieGenre} from "../../types/global.ts"
import SubmitButton from "../atoms/SubmitButton.vue"
import ProfilesService from "../../services/ProfilesService.ts"
import TableOfProfiles from "../organisms/TableOfProfiles.vue"
import {onMounted, ref} from "vue"

const toSubmit = {
  isAdult: false,
  genreIdsList: [],
};

const profilesService = new ProfilesService()
const profiles = ref([])
const desiredGenres = ref([])

const handleSubmit = () => {
  toSubmit.genreIdsList = desiredGenres.value.join(',')
  console.log(toSubmit)

  profilesService.addProfile(toSubmit);
}

onMounted(async () => {
  profiles.value = await profilesService.getProfiles()
})
</script>

<style scoped>
form {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
  justify-content: center;
}

.custom-v-select div {
  height: 2rem !important;
  max-height: 2rem !important;
}
</style>