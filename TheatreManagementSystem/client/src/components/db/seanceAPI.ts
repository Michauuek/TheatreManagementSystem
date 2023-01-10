import axios from "axios";
import { Get, Post } from "./axiosFetch";

export const seanceServiceURL = "http://127.0.0.1:8084/";

export type seanceProps = {
  id?: number,
  hallName: string,
  performanceId: number,
  seanceDate: string,
  seanceTime: string,
};
export type seanceExtendedProps = {
  id: number,
  hallName: string,
  performanceId: number,
  seanceDate: string,
  seanceTime: string,
  title: string,
  description: string,
  castId: number,
  imageUrl: string,
  length: number,
}
export type actorCastProps = {
  actorId: number,
  name: string,
  surname: string,
  photoUrl: string,
  performanceId: number,
  role: string,
}


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
  const api = Get<seanceProps[]>(seanceServiceURL + "seance/all");

  return api
    .then((data) => {
      return data.filter(validateSeance);
    })
    .catch((e) => {
      console.log(e);
      return [] as seanceProps[];
    });
}

export function AddSeance(sance: seanceProps): Promise<void> {
  const api = Post<any>(seanceServiceURL + "seance/add", JSON.stringify(sance));


  return api.
    then((data) => {

    })
    .catch((e) => {

    })
}

export async function getExtendedSeancesByDate(date: Date): Promise<seanceExtendedProps[]> {
  // const api = async () => {
  //   const data = await fetch(
  //       seanceServiceURL+"seance/get-detailed-seances-range?" + new URLSearchParams({
  //         from:date.toISOString().split('T')[0],
  //         to:date.toISOString().split('T')[0],
  //       }).toString(),
  //       {
  //           method: "GET"
  //       }
  //   );
  //   return await data.json();

  const api = Get<seanceExtendedProps[]>(seanceServiceURL +
    "seance/get-detailed-seances-range?" + new URLSearchParams({
      from: date.toISOString().split('T')[0],
      to: date.toISOString().split('T')[0],
    }).toString()
  );

  return api
    .then((data) => {
      return data;
    })
    .catch((e) => {
      return [] as seanceExtendedProps[];
    });
}

export async function getSeancesRangeByPerformanceId(date: Date, performanceId: number): Promise<seanceProps[]> {
  const now = new Date();
  const api = async () => {
    const data = await fetch(
      seanceServiceURL + "seance/get-seances-range-by-performance-id?" + new URLSearchParams({
        from: now.toISOString().split('T')[0],
        to: date.toISOString().split('T')[0],
        id: performanceId.toString(),
      }).toString(),
      {
        method: "GET"
      }
    );
    // axios
    // const data = await axios.get(seanceServiceURL + "seance/get-seances-range-by-performance-id")

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

export async function getSeancesBySeanceId(seanceId: number): Promise<seanceExtendedProps> {
  const api = async () => {
    const data = await fetch(
      seanceServiceURL + "seance/get-detailed?" + new URLSearchParams({
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


export async function deleteSeanceById(seanceId: number) {
  const api = async () => {
    const data = await fetch(
      seanceServiceURL + "seance/delete/" + seanceId,
      {
        method: "DELETE"
      }
    );
    return await data.json();
  };

  return api()
    .then((data) => {
      return (data as String);
    })
    .catch((_) => {
      throw new Error("Seance not found");
    });
}