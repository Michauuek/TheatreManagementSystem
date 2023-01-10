import { Get } from "../common/axiosFetch";
import { Fails } from "../common/failable";

const hallURL: string = "http://127.0.0.1:8082";

const getHallURL: string = hallURL + "/hall/";

enum seatState {
    FREE,
    RESERVED,
    SELECTED,
}
export default seatState;
export type seatProps = {
    id?: number,
    seatName: string,
    posX: number,
    posY: number,
    state?: seatState,
}
export type hallProps = {
    hallName: string,
    background: string,
    seatScale: number,
    seats: seatProps[],
}

export async function getHalls(): Promise<Fails<hallProps[]>> {
    const api = Get<hallProps[]>(hallURL + "/hall/all");

    return api
        .then((data) => {
            return { isOk: true, value: data };
        })
        .catch((_) => {
            return { isOk: false, value: [] };
        });
}

export async function getHall(hallName: string): Promise<hallProps> {
    const api = async () => {
        const data = await fetch(
            getHallURL + hallName,
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as hallProps);
        })
}

type hallWithStatusResponse = {
    seatsResponse: hallProps,
    reservedSeats: number[],
}

export async function getHallLayoutWithStatus(seanceId: number): Promise<Fails<hallProps>> {
    const api = Get<hallWithStatusResponse>(hallURL + "/hall/with-status?" + new URLSearchParams({id: seanceId.toString()}).toString());

    return api
    .then((raw) => {
        let hall = raw.seatsResponse;

        let reservedSeats = raw.reservedSeats;

        hall.seats.forEach((row) => {
            row.state = reservedSeats.includes(row.id || NaN) ? seatState.RESERVED : seatState.FREE;
        });

        return {isOk: true, value: hall};
    }).catch((error) => {
        return {isOk: false, value: {hallName: "", background: "", seatScale: 0, seats: []}, message: JSON.stringify(error)};
    });
}