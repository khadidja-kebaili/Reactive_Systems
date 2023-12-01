// Plugins
import { createPinia } from "pinia";
import type { App } from "vue";
import router from "../router";
import vuetify from "./vuetify";
import { loadFonts } from "./webfontloader";

const pinia = createPinia();

export function registerPlugins(app: App) {
  loadFonts();
  app.use(vuetify).use(router).use(pinia);
}
