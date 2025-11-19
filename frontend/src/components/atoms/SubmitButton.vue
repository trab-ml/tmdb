<template>
  <v-btn
      class="d-flex mx-auto bg-grey-lighten-2 sexier"
      type="submit"
      aria-disabled="true"
  >
    {{ props.value }}
  </v-btn>
</template>

<script setup lang="ts">
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

.v-btn:disabled {
  cursor: not-allowed !important;
  color: grey !important;
  border-color: grey !important;
  background-color: #e0e0e0 !important;
}
</style>