import { getFlight } from "@/services/flightService";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

export default {
  name: "Flight",
  data() {
    return {};
  },
  setup() {
    const route = useRoute();
    const flightNumber = ref(route.params.id.toString());
    const airlineName = ref("");
    const from = ref("");
    const to = ref("");
    const gate = ref("");
    const arrivalTime = ref("");
    const estimatedArrivalTime = ref("");

    onMounted(async () => {
      await getFlight(flightNumber.value)
        .then((response) => {
          airlineName.value = response.airlineName;
          from.value = response.from;
          to.value = response.to;
          gate.value = response.gate;
          arrivalTime.value = response.arrivalTime;
          estimatedArrivalTime.value = response.estimatedArrivalTime;
        })
        .catch((error) => {
          console.log("Error on getAirports()", error);
        });
    });

    return {
      flightNumber,
      airlineName,
      from,
      to,
      gate,
      arrivalTime,
      estimatedArrivalTime,
    };
  },
};
