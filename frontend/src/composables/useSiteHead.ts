import { useHead, useSeoMeta } from "@unhead/vue";
import {
    useSchemaOrg,
    definePerson,
    defineWebPage,
    defineWebSite,
} from "@unhead/schema-org/vue";
import { useI18n } from "vue-i18n";
import CONTACT from "../data/contact.json";

export function useSiteHead(titleKey: string, descriptionKey: string) {
    const { t } = useI18n();
    const META_TITLE = () => t(titleKey);
    const META_DESCRIPTION = () => t(descriptionKey);
    const PROJECT_NAME = "Tourcoing Movie Data Base";
    const SITE_URL = CONTACT.website;
    const AUTHOR_NAME = CONTACT.author;
    const LINKEDIN_URL = CONTACT.linkedin;
    const OG_IMAGE = `${SITE_URL}software-engineer.png`;

    useHead({
        htmlAttrs: {
            lang: "fr",
            "data-theme": "light",
        },
        link: [
            {
                rel: "canonical",
                href: SITE_URL,
            },
        ],
        meta: [
            {
                name: "google-site-verification",
                content: "XvspJhasrnrzaznK-j73RS7oVxx9uB2T8OBgAW9cB7w",
            },
            {
                name: "robots",
                content: "index,follow",
            },
            {
                name: "referrer",
                content: "strict-origin-when-cross-origin",
            },
        ],
    });

    useSeoMeta({
        title: META_TITLE,
        description: META_DESCRIPTION,

        ogType: "website",
        ogLocale: "fr_FR",
        ogUrl: SITE_URL,
        ogSiteName: PROJECT_NAME,
        ogTitle: META_TITLE,
        ogDescription: META_DESCRIPTION,
        ogImage: OG_IMAGE,
        ogImageWidth: 1200,
        ogImageHeight: 630,
        ogImageType: "image/png",
        ogImageAlt: META_TITLE,

        twitterCard: "summary_large_image",
        twitterTitle: META_TITLE,
        twitterDescription: META_DESCRIPTION,
        twitterImage: OG_IMAGE,
    });

    useSchemaOrg([
        defineWebSite({
            "@id": `${SITE_URL}#app`,
            url: SITE_URL,
            name: "Tourcoing Movies Data Base",
            description: META_DESCRIPTION,
            inLanguage: "fr-FR",
            publisher: {
                "@id": `${SITE_URL}#person`,
            },
        }),

        defineWebPage({
            "@id": `${SITE_URL}#webpage`,
            url: SITE_URL,
            name: META_TITLE,
            description: META_DESCRIPTION,
            inLanguage: "fr-FR",
            isPartOf: {
                "@id": `${SITE_URL}#app`,
            },
            about: {
                "@id": `${SITE_URL}#person`,
            },
        }),

        definePerson({
            "@id": `${SITE_URL}#person`,
            name: AUTHOR_NAME,
            url: SITE_URL,
            image: OG_IMAGE,
            jobTitle: META_TITLE,
            sameAs: [LINKEDIN_URL],
        }),
    ]);
}
