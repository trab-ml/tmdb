<template>
  <div style="display: flex; flex-direction: row; align-items: center; justify-content: center">
    <span>{{ props.titled }}</span>
    <select
        v-model="selectedValues"
        @change="onChange"
        :multiple="props.isMultiSelect"
        class="w-100 d-flex pa-1 rounded-lg border-solid"
        onsubmit="emits('getSelectedValues', selectedValues)"
        :disabled="props.disable"
        style="margin: 0 .5rem">
      <option value="">{{ props.disable && props.disable === true ? defaultSelectedValue : "" }}</option>
      <option v-for="sport in props.items" :value="sport.id">{{sport.name}}</option>
    </select>
  </div>
</template>

<script setup>
import {ref, watch} from "vue";

const props = defineProps({
  titled: String,
  items: Array,
  isMultiSelect: Boolean,
  variant: String,
  defaultValue: String,
  disable: Boolean
});

const emits = defineEmits(['getSelectedValues']);
const defaultSelectedValue = ref(props.defaultValue || "");
const selectedValues = ref(defaultSelectedValue || props.defaultValue || []);

const onChange = () => {
  emits('getSelectedValues', selectedValues.value);
};

watch(props, (newValue, oldValue) => {
  defaultSelectedValue.value = newValue.defaultValue;
});
</script>
