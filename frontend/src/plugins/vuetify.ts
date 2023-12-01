// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";
import { aliases, mdi } from "vuetify/iconsets/mdi";

// Composables
import { createVuetify } from "vuetify";

// Set theme based on system theme
const storedTheme = localStorage.getItem("reactive-system-theme");
const systemTheme =
  storedTheme !== null
    ? storedTheme
    : window.matchMedia("(prefers-color-scheme: dark)").matches
    ? "dark"
    : "light";

export default createVuetify({
  theme: {
    defaultTheme: systemTheme,

    themes: {
      light: {
        colors: {
          // add the colors which should be changed from the default
          //primary: "#3f51b5",
        },
      },
      dark: {
        colors: {
          // add the colors which should be changed from the default
          // primary: "#3f51b5",
        },
      },
    },
  },
  icons: {
    defaultSet: "mdi",
    aliases,
    sets: {
      mdi,
    },
  },
});
