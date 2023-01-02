import axios from "axios";

const addReservationURL: string      = "http://127.0.0.1:8083/reservation/add";
const addReservationOauthURL: string = "http://127.0.0.1:8083/reservation/add-auth";

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
    const data = await axios.post(addReservationOauthURL, {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
      data: reservation,
    });
    return data.data;
  };

  return api()
    .then((response) => {})
    .catch((_) => {});

}
