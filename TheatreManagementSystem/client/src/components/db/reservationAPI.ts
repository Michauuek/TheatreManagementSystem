import axios from "axios";
import { Result } from "../common/failable";
import { reservationProps } from "./DBModel";

const addReservationURL: string             = "http://127.0.0.1:8083/reservation/add";
const addReservationOauthURL: string        = "http://127.0.0.1:8083/reservation/add-auth";
const getAllReservationForSeanceURL: string = "http://127.0.0.1:8083/reservation/all-reservations/";

export type ReservationRequest = {
  seanceId: number;
  clientName: string;
  clientEmail: string;
  clientPhone: string;
  reservedSeats: number[];
};

export type ReservationViaOauthRequest = {
  seanceId: number;
  reservedSeats: number[];
};
export type ReservationResponse = {};



export async function makeReservation(reservation: ReservationRequest) {
  const api = async () => {
    const response = await fetch(addReservationURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(reservation),
    });
    return await response.json();
  };

  return api()
    .then((response) => {console.log(response);})
    .catch((err) => {console.log(err);});
}

export async function makeReservationViaOauth(reservation: ReservationViaOauthRequest) {
  const api = async () => {
    const data = await axios.post(addReservationOauthURL, JSON.stringify(reservation));
    return data.data;
  };

  return api()
    .then((response) => {})
    .catch((_) => {});
}



export type AllReservationsResponse = {
  seanceId: number;
  reservations: reservationProps[];
};
export type AllReservationError = {
  message: string;
  type: "UNAUTHORIZED"|"BAD_SEANCEID"|"OTHER";
};
export type AllReservations = Result<AllReservationsResponse, AllReservationError>;

export async function getReservationBySeanceId(seanceId: number): Promise<AllReservations> {
  const api = async () => {
    const data = await axios.get(getAllReservationForSeanceURL + seanceId, {
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    return data.data;
  }

  //todo
  return api()
  .catch((error) => 
  {
    if (error.response.status === 401) {
      return {isOk: false, message: "Unauthorized", type: "UNAUTHORIZED"} as AllReservations;
    } else if (error.response.status === 404) {
      return {isOk: false, message: "Bad seanceId", type: "BAD_SEANCEID"} as AllReservations;
    } else {
      return {isOk: false, message: "Unexpected response from server", type: "OTHER"} as AllReservations;
    }
  })
  .then((data) => {
    let response = data as AllReservationsResponse;

    if (response.seanceId === seanceId) {
      return {isOk: true, reservations: response.reservations} as AllReservations;
    } else {
      return {isOk: false, message: "Unexpected response from server", type: "OTHER"}  as AllReservations;
    }
  });
}