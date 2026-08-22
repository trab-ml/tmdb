import { routes } from "./router";
import { i18n } from "./utils/translate/translate";

import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import App from "./App.vue";

import { aliases } from "vuetify/iconsets/fa";
import { mdi } from "vuetify/iconsets/mdi";
import "@mdi/font/css/materialdesignicons.css";
import { ViteSSG } from "vite-ssg";
import { createHead } from "@unhead/vue/client";

const vuetify = createVuetify({
    icons: {
        defaultSet: "mdi",
        aliases,
        sets: {
            mdi,
        },
    },
    components,
    directives,
    theme: {
        defaultTheme: "light",
    },
});

export const createApp = ViteSSG(App, { routes: routes }, ({ app }) => {
    const head = createHead();
    app.use(i18n);
    app.use(head);
    app.use(vuetify);
});
