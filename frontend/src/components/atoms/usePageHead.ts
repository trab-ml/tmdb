import { useHead } from "@unhead/vue";
import { useI18n } from "vue-i18n";

export function usePageHead(titleKey: string, descriptionKey: string) {
    const { t } = useI18n();

    useHead({
        title: t(titleKey),
        meta: [{ name: "description", content: t(descriptionKey) }],
    });
}
