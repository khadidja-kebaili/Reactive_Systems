import axios from "axios";

const backendUrl = import.meta.env.VITE_BACKEND_URL + "/flight";

export async function getAllFlights() {
  return await axios
    .get(backendUrl)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error;
    });
}

export async function getFlight(id: String) {
  return await axios
    .get(backendUrl + "/" + id)
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      return error;
    });
}
