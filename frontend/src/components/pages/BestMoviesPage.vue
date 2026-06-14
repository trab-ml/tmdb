<template>
    <Header />
    <main>
        <h1>{{ t("popularMoviesPage.title") }}</h1>

        <form action="" @submit.prevent="handleSubmit">
            <div
                v-if="profiles.length > 0"
                class="d-flex flex-row align-center form-container"
                style="
                    display: flex;
                    border: 1px solid #e0e0e0;
                    border-radius: 1.5rem 1.5rem 0.1rem 0.1rem;
                    border-bottom: none;
                "
            >
                <TableOfProfiles
                    v-model="selectedProfile"
                    :items="profiles"
                    :formMode="true"
                    style="width: 75%"
                />
                <div
                    style="
                        width: 25%;
                        display: flex;
                        flex-direction: row;
                        justify-items: center;
                        align-items: center;
                    "
                    id="form-actions"
                >
                    <SubmitButton
                        :value="t('popularMoviesPage.submitButtonText')"
                        class="search-button"
                        style="margin: 0 auto"
                        :disabled="selectedProfile === ''"
                    />
                    <button
                        v-if="selectedProfile !== ''"
                        variant="outlined"
                        @click="resetProfile()"
                        class="reset-filter"
                    >
                        {{ t("popularMoviesPage.resetButtonText") }}
                        <img
                            :src="koIcon"
                            :alt="t('popularMoviesPage.resetButtonText')"
                        />
                    </button>
                </div>
            </div>
            <p v-else>
                {{ t("popularMoviesPage.emptyProfileList.preLink") }}
                <RouterLink to="/profile">{{
                    t("popularMoviesPage.emptyProfileList.link")
                }}</RouterLink>
                {{ t("popularMoviesPage.emptyProfileList.postLink") }}
            </p>
        </form>

        <MoviesContainer :movieList="movieList" />
    </main>
</template>

<script setup lang="ts">
import Header from "../layouts/Header.vue";
import MoviesService from "../../services/MoviesService.ts";
import { onMounted, ref } from "vue";
import TableOfProfiles from "../organisms/TableOfProfiles.vue";
import ProfilesService from "../../services/ProfilesService.ts";
import SubmitButton from "../atoms/SubmitButton.vue";
import MoviesContainer from "../organisms/MoviesContainer.vue";
import { useI18n } from "vue-i18n";
import type { IMovie, IProfile } from "../../types/global.ts";
import koIcon from "/src/assets/not-ok-32px.png";

const { t } = useI18n();
const movieList = ref<IMovie[]>([]);
const moviesService = new MoviesService();
const profilesService = new ProfilesService();
const profiles = ref<IProfile[]>([]);
const selectedProfile = ref<string>("");

const fetchMoviesAndProfiles = async () => {
    movieList.value = await moviesService.getMovies(true);
    profiles.value = await profilesService.getProfiles();
};

const resetProfile = async () => {
    selectedProfile.value = "";
    await fetchMoviesAndProfiles();
};

onMounted(async () => {
    await fetchMoviesAndProfiles();
    const navigationEntry = performance.getEntriesByType(
        "navigation",
    )[0] as PerformanceEntry;
    if (navigationEntry.toString() == "navigate") {
        setTimeout(() => {
            alert(t("popularMoviesPage.firstLoadAlertMessage"));
        }, 300);
    }
});

const handleSubmit = async () => {
    movieList.value = await moviesService.searchByProfile(
        selectedProfile.value,
    );
};
</script>

<style scoped>
.search-button,
.reset-filter {
    cursor: pointer;
    display: flex;
    justify-self: center !important;
    align-items: center;
    margin: 2rem 0;
    border: 1px solid;
    border-radius: 1rem;
    text-transform: uppercase;
}

.search-button:hover {
    filter: drop-shadow(0 0 0.75rem var(--main-color));
}

.reset-filter:hover {
    color: var(--classic-red);
}

@media screen and (max-width: 992px) {
    .form-container {
        align-items: center;
    }

    .form-container #form-actions {
        flex-direction: column !important;
        gap: 1rem;
    }
}

@media screen and (min-width: 992px) {
    #form-actions .reset-filter {
        margin-right: 1rem;
    }
}
</style>
