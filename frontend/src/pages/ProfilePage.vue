<template>
  <Header />
  <main>
    <h1>{{$t('profilePage.title')}}</h1>

    <form
        action=""
        @submit.prevent="handleSubmit"
        style="border: 1px solid; border-radius: 1rem; padding: 1rem 0">
      <div>
        <CheckboxField label="Adult (- 18 ans?)" @getInputValue="(v: Boolean) => toSubmit.isAdult = v" />
      </div>
      <div>
        <SelectField titled="Genre" :items="getMovieGenres()" :isMultiSelect="true" @getSelectedValues="(values: Array<Object>) => toSubmit.genreIdsList = values" />
      </div>
      <SubmitButton class="mt-3" value="Envoyer"/>
    </form>
  </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue"
import CheckboxField from "../atoms/CheckboxField.vue"
import SelectField from "../atoms/SelectField.vue"
import {getMovieGenres} from "../types/global.ts"
import SubmitButton from "../atoms/SubmitButton.vue";
import ProfilesService from "../services/ProfilesService.ts";

const toSubmit = {
  isAdult: false,
  genreIdsList: [],
};

const profilesService = new ProfilesService()

const handleSubmit = () => {
  toSubmit.genreIdsList = toSubmit.genreIdsList.join(',')
  console.log(toSubmit)

  profilesService.addProfile(toSubmit);

  // window.location.href = "http://localhost:5173/"
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
  justify-content: center;
}
</style>