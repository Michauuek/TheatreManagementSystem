import { performanceProps } from "./DBModel";


export async function getPerformance(): Promise<performanceProps[]> {
    const api = async () => {
        const data = await fetch(
            "http://127.0.0.1:8084/performance/all",
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as performanceProps[]);
        })
        .catch((_) => {
            return [] as performanceProps[];
        });
}

export async function getPerformanceById(id:number): Promise<performanceProps> {
    const api = async () => {
        const data = await fetch(
            "http://127.0.0.1:8084/performance/" + id.toString(),
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return (data as performanceProps);
        })
        .catch((error) => {
            throw new Error(error);
        });
}
