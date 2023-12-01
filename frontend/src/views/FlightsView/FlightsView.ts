import router from "@/router";
import { getAllFlights } from "@/services/flightService";
import { onMounted, ref } from "vue";

export default {
  name: "Flights",
  data() {
    return {};
  },
  setup() {
    const flights = ref();

    onMounted(async () => {
      await getAllFlights()
        .then((response) => {
          flights.value = response;
        })
        .catch((error) => {
          console.log("Error on getAirports()", error);
        });
    });

    return { flights };
  },
  methods: {
    goToFlight(id: String) {
      router.push(`/flight/${id}`);
    },
  },
};
