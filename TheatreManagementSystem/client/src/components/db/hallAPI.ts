import { hallProps, seatState } from "./DBModel";

const hallURL: string = "http://127.0.0.1:8082";

const getHallsURL: string = hallURL + "/hall/all";
const getHallURL: string  = hallURL + "/hall/";


export async function getHalls(): Promise<hallProps[]> {
    const api = async () => {
        const data = await fetch(
            getHallsURL,
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as hallProps[]);
        })
        .catch((_) => {
            return [] as hallProps[];
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

export async function getHallLayoutWithStatus(seanceId: number): Promise<hallProps> {
    const api = async () => {
        const data = await fetch(
            "http://127.0.0.1:8082/hall/with-status?" + new URLSearchParams({
                id: seanceId.toString(),
        }).toString(),
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api().then((data) => {
        console.log(data);
        let raw = (data as hallWithStatusResponse);
        
        let hall = raw.seatsResponse;
        
        let reservedSeats = raw.reservedSeats;
        

        hall.seats.forEach((row) => { 
            row.state = reservedSeats.includes(row.id || NaN) ? seatState.RESERVED : seatState.FREE;
        });

        return hall;
    }).catch((error) => {
        // todo return default, empty hall.
        throw new Error(error);
    });
}