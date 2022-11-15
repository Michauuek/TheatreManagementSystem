import { useEffect } from "react";
import { seanceProps } from "./dataBaseModel";


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
        const data = await fetch(
            "http://127.0.0.1:8080/seance/all",
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as seanceProps[]).filter(validateSeance);
        })
        .catch((_) => {
            return [] as seanceProps[];
        });
}

