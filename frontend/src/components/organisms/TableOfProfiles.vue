<template>
  <h2>{{$t('tableOfProfiles.title')}}</h2>
  <v-table class="result-table rounded-lg border-solid" height="300px">
    <thead>
    <tr>
      <th class="text-left font-weight-bold">{{ $t('tableOfProfiles.header.th1') }}</th>
      <th class="text-left font-weight-bold">{{ $t('tableOfProfiles.header.th2') }}</th>
      <th class="text-left font-weight-bold">{{ $t('tableOfProfiles.header.th3') }}</th>
    </tr>
    </thead>

    <tbody>
    <tr
        v-for="(item, index) in props.items"
        :key="item.name"
    >
      <td>{{ index }}</td>
      <td>
        <img
            v-if="item.adult === true"
            :src="okIcon"
            :alt="$t('tableOfProfiles.body.major')"/>
        <img
            v-else
            :src="koIcon"
            :alt="$t('tableOfProfiles.body.minor')"/>
      </td>
      <td>
        <div class="text-center">
          <v-menu open-on-hover>
            <template v-slot:activator="{ props }">
              <p v-bind="props">
                <img :src="takeALookIcon" :alt="$t('tableOfProfiles.body.takeALook')">
              </p>
            </template>

            <v-list>
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
                <v-list-item-title>{{ $t('tableOfProfiles.body.noAssociatedGenre') }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </td>
    </tr>
    </tbody>
  </v-table>
</template>

<script setup>
import okIcon from "/src/assets/ok-32px.png"
import koIcon from "/src/assets/not-ok-32px.png"
import takeALookIcon from "/src/assets/take-look-32px.png"

const props = defineProps({
  items: Array,
  formMode: Boolean
});
</script>

<style scoped>
@media screen and (min-width: 600px) {
  .result-table {
    width: 100%;
  }
}
@media screen and (min-width: 960px) {
  .result-table {
    height: 600px !important;
  }
}
</style>