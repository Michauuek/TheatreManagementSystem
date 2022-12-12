

export type seanceProps = {
    id?: number,
    hallName: string,
    performanceId: number,
    seanceDate: string,
    seanceTime: string,
};

export type hallWithSeance = {
    seanceInfo?: seanceProps,
}&hallProps;

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
    backgroud: string,
    seats: seatProps[],
}

export type performanceProps = {
    id?: number,
    title: string,
    description: string,
    imageUrl: string
}

export type reservationProps = {
    performanceId?: number,
    reservationDate: string,
    reservationTime: string,
    clientName: string,
    clientEmail: string,
    seance?: seanceProps,
}

