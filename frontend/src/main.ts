import { createApp } from 'vue'
import router from "./router.ts";
import { i18n } from "./utils/translate/translate.ts"

// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// Components
import App from './App.vue'

const vuetify = createVuetify({
    icons: {
        defaultSet: 'mdi', // This is already the default value - only for display purposes
    },
    components,
    directives,
    theme: {
        defaultTheme: 'light',
    },
})

const app = createApp(App);

app.use(router);
app.use(i18n);
app.use(vuetify).mount('#app');
