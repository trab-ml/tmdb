<template>
  <v-btn
      class="d-flex mx-auto bg-grey-lighten-2"
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