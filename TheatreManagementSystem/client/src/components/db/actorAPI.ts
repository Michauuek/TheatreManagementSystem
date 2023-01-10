import { Get } from "../common/axiosFetch";
import { Fails } from "../common/failable";
import { actorCastProps, seanceServiceURL } from "./seanceAPI";



export async function getCastByPerformanceId(id:number): Promise<Fails<actorCastProps[]>> {
    const api = Get<actorCastProps[]>(seanceServiceURL + "ctor/by-performance-id/" + id);

    return api
        .then((data) => {
            return {isOk: true, value: data};
        })
        .catch((_) => {
            return {isOk: false, value: []};
        });
}
