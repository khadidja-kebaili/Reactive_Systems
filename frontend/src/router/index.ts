// Composables
import { createRouter, createWebHistory } from "vue-router";
import Layout from "@/views/Layout/index.vue";
import HomeView from "@/views/Home/index.vue";
import NotFoundPage from "@/views/NotFoundPage/index.vue";
import ImpressumView from "@/views/Impressum/index.vue";
import AirportsView from "@/views/AirportsView/index.vue";
import FlightsView from "@/views/FlightsView/index.vue";
import FlightView from "@/views/FlightView/index.vue";
import StatsView from "@/views/StatsView/index.vue";

const routes = [
  {
    path: "/:pathMatch(.*)*",
    component: NotFoundPage,
  },
  {
    path: "/",
    redirect: "/home",
    component: Layout,
    children: [
      {
        path: "home",
        name: "Home",
        component: HomeView,
      },
      {
        path: "airports",
        name: "Airports",
        component: AirportsView,
      },
      {
        path: "flights",
        name: "Flights",
        component: FlightsView,
      },
      {
        path: "flight/:id",
        name: "Flight",
        component: FlightView,
      },
      {
        path: "stats",
        name: "Stats",
        component: StatsView,
      },
      {
        path: "impressum",
        name: "Impressum",
        component: ImpressumView,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return {
        savedPosition,
        behavior: "smooth",
      };
    } else {
      return {
        top: 0,
        behavior: "smooth",
      };
    }
  },
});

export default router;
