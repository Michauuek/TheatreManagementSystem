

export type seanceProps = {
    id?: number,
    hallName: string;
    performanceId: number;
    seanceDate: string;
    seanceTime: string;
};

export type seatProps = {
    id?: number,
    hallName: string,
    seatName: string,
    posX: number,
    posY: number,
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
    //todo CAST 
}

export type reservationProps = {
    id?: number,
    reservationDate: string,
    reservationTime: string,
    clientName: string,
    clientEmail: string,
    seance?: seanceProps,
}

