import {createI18n} from "vue-i18n";
import {fr} from "./fr.ts";
import {en} from "./en.ts";

export const i18n = createI18n({
    locale: 'fr',
    fallbackLocale: 'en',
    messages: {
        fr: fr,
        en: en,
    }
});
