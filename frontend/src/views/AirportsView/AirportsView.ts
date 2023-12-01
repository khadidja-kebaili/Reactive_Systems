import { getAllAirports } from "@/services/airportService";
import { onMounted, ref } from "vue";

export default {
  name: "AirportsView",
  data() {
    return {};
  },
  setup() {
    const airports = ref();

    onMounted(async () => {
      await getAllAirports()
        .then((response) => {
          airports.value = response;
        })
        .catch((error) => {
          console.log("Error on getAirports()", error);
        });
    });

    return { airports };
  },
};
