import { hallProps } from "./DBModel";


export async function getHalls(): Promise<hallProps[]> {
    const api = async () => {
        const data = await fetch(
            "http://127.0.0.1:8084/hall/all",
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
            "http://127.0.0.1:8084/hall/" + hallName,
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