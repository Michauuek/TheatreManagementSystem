import axios from "axios";
import { Result } from "../common/failable";
import { Get, Post } from "./axiosFetch";
import { seanceProps } from "./seanceAPI";

const ReservationsURL = "http://127.0.0.1:8083";




export type reservationProps = {
  performanceId?: number,
  reservationDate: string,
  reservationTime: string,
  clientName: string,
  clientEmail: string,
  clientPhone?: string,
  seance?: seanceProps,
}

/**
 * todo docs
 */
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
export type ReservationResponse = {
  status: "OK" | "SEATS_ALREADY_RESERVED" | "INVALID_NAME" | "INVALID_EMAIL" | "INVAILD_NUMBER" | "OTHER_ERROR"
};

export async function makeReservation(reservation: ReservationRequest) {
  const api = Post<any>(ReservationsURL + "/reservation/add", JSON.stringify(reservation));

  return api;
}

export async function makeReservationViaOauth(reservation: ReservationViaOauthRequest) {
  const api = Post<ReservationResponse>(ReservationsURL + "/reservation/add-auth", JSON.stringify(reservation));

  return api;
}



export type AllReservationsResponse = {
  seanceId: number;
  reservations: reservationProps[];
};
export type AllReservationError = {
  message: string;
  type: "UNAUTHORIZED" | "BAD_SEANCEID" | "OTHER";
};
export type AllReservations = Result<AllReservationsResponse, AllReservationError>;

export async function getReservationBySeanceId(seanceId: number): Promise<AllReservations> {
  const api = Get<AllReservationsResponse>(ReservationsURL + "/reservation/all-reservations/" + seanceId);

  //todo
  return api.then((data) => {
    let response = data;

    if (response.seanceId === seanceId) {
      return { isOk: true, reservations: response.reservations } as AllReservations;
    } else {
      return { isOk: false, message: "Unexpected response from server", type: "OTHER" } as AllReservations;
    }
  })
    .catch((error) => {
      if (error.response.status === 401) {
        return { isOk: false, message: "Unauthorized", type: "UNAUTHORIZED" } as AllReservations;
      } else if (error.response.status === 404) {
        return { isOk: false, message: "Bad seanceId", type: "BAD_SEANCEID" } as AllReservations;
      } else {
        return { isOk: false, message: "Unexpected response from server", type: "OTHER" } as AllReservations;
      }
    });

}