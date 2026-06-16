import { routes } from "./router.ts";
import { i18n } from "./utils/translate/translate.ts";

import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import App from "./App.vue";

import { aliases } from "vuetify/iconsets/fa";
import { mdi } from "vuetify/iconsets/mdi";
import "@mdi/font/css/materialdesignicons.css";
import { ViteSSG } from "vite-ssg";

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
    app.use(i18n);
    app.use(vuetify);
});
