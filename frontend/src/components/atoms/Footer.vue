<template>
    <footer>
        <a
            :href="t('footer.portfolio.link')"
            target="_blank"
            style="color: #fff"
            >{{ t("footer.portfolio.title") }}</a
        >
    </footer>
</template>

<script setup lang="ts">
import { useI18n } from "vue-i18n";

const { t } = useI18n();
</script>

<style scoped>
footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 2rem;
    color: #000;
    display: flex;
    justify-content: center;
    align-items: center;
    border-top: 1px solid;
    border-radius: 1rem;
}

footer a {
    color: #000 !important;
}

footer a:hover {
    color: var(--main-color) !important;
}
</style>
