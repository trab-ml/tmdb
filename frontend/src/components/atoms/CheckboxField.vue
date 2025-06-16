<template>
  <label>
    <input type="checkbox" @change="onChange" v-model="value">
    {{ props.label }}
  </label>
</template>

<script setup lang="ts">
import {ref} from "vue";

const props = defineProps<{
  'label': string
}>();
const value = ref(false);
const emits = defineEmits(['getInputValue']);
const onChange = (event) => {
  emits('getInputValue', event.currentTarget.checked);
}
</script>