export const seanceServiceURL : string = "http://127.0.0.1:8084/";
export const hallServiceURL : string = "http://127.0.0.1:8084/";
export const performanceServiceURL : string = "http://127.0.0.1:8084/";
export const authServiceURL : string = "http://127.0.0.1:8084/";


export type seanceProps = {
    id?: number,
    hallName: string,
    performanceId: number,
    seanceDate: string,
    seanceTime: string,
};

export enum seatState {
    FREE,
    RESERVED,
    SELECTED,
}
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
    seats: seatProps[],
}

export type performanceProps = {
    performanceId: number,
    title: string,
    description: string,
    imageUrl: string,
    length: number,
}


export type reservationProps = {
    performanceId?: number,
    reservationDate: string,
    reservationTime: string,
    clientName: string,
    clientEmail: string,
    clientPhone?: string,
    seance?: seanceProps,
}


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
