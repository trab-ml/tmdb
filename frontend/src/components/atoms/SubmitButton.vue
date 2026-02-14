<template>
    <button
        class="d-flex mx-auto bg-grey-lighten-2 sexier"
        type="submit"
        aria-disabled="true"
    >
        {{ props.value }}
    </button>
</template>

<script setup lang="ts">
const props = defineProps({
    value: String,
});
</script>

<style>
.sexier {
    background: none !important;
    color: inherit !important;
    cursor: pointer;
    padding: 0.65rem;
}

.sexier:hover,
.sexier:active {
    color: #f2f2f2 !important;
    background-color: #000 !important;
}

.sexier:disabled {
    cursor: not-allowed !important;
    color: grey !important;
    border-color: grey !important;
    background-color: #e0e0e0 !important;
}
</style>
