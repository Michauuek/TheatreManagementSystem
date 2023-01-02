import axios from "axios";
import { seanceExtendedProps, seanceProps, seanceServiceURL } from "./DBModel";

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
  const data = await axios.get(seanceServiceURL + "seance/all", {
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

export async function getExtendedSeancesByDate(date: Date): Promise<seanceExtendedProps[]> {
  const api = async () => {
    const data = await fetch(
        seanceServiceURL+"seance/get-detailed-seances-range?" + new URLSearchParams({
          from:date.toISOString().split('T')[0],
          to:date.toISOString().split('T')[0],
        }).toString(),
        {
            method: "GET"
        }
    );
    return await data.json();
};

return api()
    .then((data) => {
        return (data as seanceExtendedProps[]);
    })
    .catch((_) => {
        return [] as seanceExtendedProps[];
    });
}

export async function getSeancesRangeByPerformanceId(date: Date, performanceId : number): Promise<seanceProps[]> {
  const now = new Date();
  const api = async () => {
    const data = await fetch(
        seanceServiceURL+ "seance/get-seances-range-by-performance-id?" + new URLSearchParams({
          from:now.toISOString().split('T')[0],
          to:date.toISOString().split('T')[0],
          id: performanceId.toString(),
        }).toString(),
        {
            method: "GET"
        }
    );
    return await data.json();
};

return api()
    .then((data) => {
        return (data as seanceProps[]);
    })
    .catch((_) => {
        return [] as seanceProps[];
    });
}

export async function getSeancesBySeanceId(seanceId : number): Promise<seanceExtendedProps> {
  const api = async () => {
    const data = await fetch(
        seanceServiceURL+ "seance/get-detailed?" + new URLSearchParams({
          id: seanceId.toString(),
        }).toString(),
        {
            method: "GET"
        }
    );
    return await data.json();
  };

  return api()
    .then((data) => {
        return (data as seanceExtendedProps);
    })
    .catch((_) => {
        throw new Error("Seance not found");
    });
}