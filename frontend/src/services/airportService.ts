import axios from "axios";

const backendUrl = import.meta.env.VITE_BACKEND_URL + "/airport";

export async function getAllAirports() {
  await axios
    .get(backendUrl)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error;
    });
}
export async function getAirport(id: String) {
  await axios
    .get(backendUrl + "/" + id)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error;
    });
}
