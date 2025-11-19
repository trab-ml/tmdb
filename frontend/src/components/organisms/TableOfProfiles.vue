<template>
  <div class="table-container">
    <h2>{{ t('tableOfProfiles.title') }}</h2>
    <v-table class="result-table rounded-lg border-solid" height="300px">
      <thead>
      <tr>
        <th class="text-left font-weight-bold">{{ t('tableOfProfiles.header.th1') }}</th>
        <th class="text-left font-weight-bold">{{ t('tableOfProfiles.header.th2') }}</th>
        <th class="text-left font-weight-bold">{{ t('tableOfProfiles.header.th3') }}</th>
      </tr>
      </thead>

      <tbody>
      <tr
          v-for="(item, index) in props.items"
          :key="item.name"
      >
        <td v-if="props.formMode">
          <label>
            <input type="radio" :id="`profile-${index}`" :value="item.id" v-model="selectedProfile" @change="onChange"/>
            n° {{ index + 1 }}
          </label>
        </td>
        <td v-else>n° {{ index + 1 }}</td>

        <td>
          <img
              v-if="item.adult"
              :src="okIcon"
              :alt="t('tableOfProfiles.body.major')"/>
          <img
              v-else
              :src="koIcon"
              :alt="t('tableOfProfiles.body.minor')"/>
        </td>
        <td>
          <div class="text-center genres-list">
            <v-menu open-on-hover>
              <template v-slot:activator="{ props }">
                <p v-bind="props">
                  <img :src="takeALookIcon" :alt="t('tableOfProfiles.body.takeALook')">
                </p>
              </template>

              <v-list style="overflow: hidden">
                <v-list-item
                    v-if="item.genreList.length > 0"
                    v-for="(genre, index) in item.genreList"
                    :key="index"
                >
                  <v-list-item-title>{{ genre }}</v-list-item-title>
                </v-list-item>

                <v-list-item
                    v-else
                >
                  <v-list-item-title>{{ t('tableOfProfiles.body.noAssociatedGenre') }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </div>
        </td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>

<script setup lang="ts">
import okIcon from "/src/assets/ok-32px.png"
import koIcon from "/src/assets/not-ok-32px.png"
import takeALookIcon from "/src/assets/take-look-32px.png"
import {ref} from 'vue'
import type { IProfile } from '../../types/global.js'
import { useI18n } from 'vue-i18n'

const props = defineProps<{
  items: IProfile[],
  formMode: boolean
}>()
const {t} = useI18n()
const selectedProfile = ref('')

const emits = defineEmits(['getInputValue']);
const onChange = () => {
  emits('getInputValue', selectedProfile.value);
}
</script>

<style scoped>
.result-table {
  margin-bottom: 2rem;
  overflow: hidden;
}

.table-container {
  display: flex;
  flex-direction: column;
  justify-items: center;
  align-items: center;
  margin: 0 !important;
}

.genres-list:hover {
  cursor: pointer;
}

@media screen and (min-width: 600px) {
  .result-table {
    width: 100%;
  }
}
</style>