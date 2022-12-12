import axios from "axios";
import { useEffect } from "react";
import Cookies from "universal-cookie";
import { performanceProps, seanceProps } from "./DBModel";

function validateSeance(seance: seanceProps) {
  if (!seance.hallName) {
    console.log("hallName is empty");
    return false;
  }
  if (!seance.performanceId) {
    console.log("performanceID is empty");
    return false;
  }
  if (!seance.seanceDate) {
    console.log("seanceDate is empty");
    return false;
  }
  if (!seance.seanceTime) {
    console.log("seanceTime is empty");
    return false;
  }
  return true;
}

export async function getSeances(): Promise<seanceProps[]> {
  const api = async () => {
  const data = await axios.get("http://127.0.0.1:8084/seance/all", {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    });
    return data.data;
  };
  
  return api()
    .then((data) => {
      return (data as seanceProps[]).filter(validateSeance);
    })
    .catch((_) => {
      return [] as seanceProps[];
    });
}

export function AddSeance(sance: seanceProps): void {
  let payload = JSON.stringify(sance);


  fetch("http://127.0.0.1:8081/auth/test", {
    credentials: "include",
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },

    body: payload,
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Success:", data);
    })
    .catch((error) => {
      throw new Error(error);
    });
}
