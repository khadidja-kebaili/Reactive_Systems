import { useTheme } from "vuetify";
import { useDisplay } from "vuetify";
import { ref } from "vue";

export default {
  name: "customAppBar",
  data() {
    return {
      features: [
        {
          title: "Home",
          route: "Home",
          icon: "mdi-home",
        },
        {
          title: "Airports",
          route: "Airports",
          icon: "mdi-airport",
        },
        {
          title: "Flights",
          route: "Flights",
          icon: "mdi-airplane",
        },
        {
          title: "Statistics",
          route: "Stats",
          icon: "mdi-finance",
        },
      ],
    };
  },
  setup() {
    const theme = useTheme();
    const { xs, lgAndUp } = useDisplay();
    const drawer = ref(lgAndUp.value ? true : false);
    const themeTitle = ref(theme.global.current.value.dark ? "Dunkel" : "Hell");

    const toggleTheme = () => {
      theme.global.name.value = theme.global.current.value.dark
        ? "light"
        : "dark";
      // If Browser supports local-storage, save theme setting
      if (typeof Storage !== "undefined") {
        localStorage.setItem("reactive-system-theme", theme.global.name.value);
      }
    };

    return {
      toggleTheme,
      xs,
      themeTitle,
      drawer,
    };
  },
};
