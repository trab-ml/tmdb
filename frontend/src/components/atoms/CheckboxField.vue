<template>
  <label>
    <input type="checkbox" @change="onChange" v-model="value">
    {{ props.label }}
  </label>
</template>

<script setup lang="ts">
import {ref} from "vue"

const props = defineProps<{
  'label': string
}>()
const value = ref(false)

interface IChangeEvent extends Event {
  currentTarget: HTMLInputElement
}

const emits = defineEmits(['getInputValue'])
const onChange = (event: Event) => {
  const {currentTarget} = event as IChangeEvent
  emits('getInputValue', currentTarget.checked)
}
</script>