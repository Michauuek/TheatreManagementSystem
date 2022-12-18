import { actorCastProps, seanceServiceURL } from "./DBModel";


export async function getCastByPerformanceId(id:number): Promise<actorCastProps[]> {
    const api = async () => {
        const data = await fetch(
            seanceServiceURL+"actor/by-performance-id/" + id.toString(),
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as actorCastProps[]);
        })
        .catch((_) => {
            return [] as actorCastProps[];
        });
}
