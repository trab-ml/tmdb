<template class="custom-actions">
  <v-btn
      class="d-flex mx-auto bg-grey-lighten-2 sexier"
      type="submit"
  >
    {{ props.value }}
  </v-btn>
</template>

<script setup>
const props = defineProps({
  value: String
});
</script>

<style>
.v-btn.sexier {
  background: none !important;
  color: inherit !important;
  cursor: pointer;
}

.v-btn.sexier:hover,
.v-btn.sexier:active {
  color: #f2f2f2 !important;
  background-color: #000 !important;
}

.v-btn.sexier .v-btn__overlay {
  opacity: 0 !important;
}
</style>