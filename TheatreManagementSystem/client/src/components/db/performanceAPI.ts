import { Delete, Get, Post } from "../common/axiosFetch";
import { Fails, Result } from "../common/failable";

const performanceURL = "http://127.0.0.1:8084";

export type performanceProps = {
    performanceId: number,
    title: string,
    description: string,
    imageUrl: string,
    length: number,
}

export type performanceRequest = {
    title: string,
    description: string,
    imageUrl: string,
    length: string,
}

export async function getPerformance(): Promise<Fails<performanceProps[]>> {
    const api = Get<performanceProps[]>(performanceURL + "/performance/all");

    return api
        .then((data) => {
            return { value: data, isOk: true } as Fails<performanceProps[]>;
        })
        .catch((_) => {
            return { value: [], isOk: false, message: "Performances cannot be found" } as Fails<performanceProps[]>;
        });
}

export type GetPerformanceByIdError = {
    message: string,
}

export async function getPerformanceById(id: number): Promise<Result<performanceProps, GetPerformanceByIdError>> {
    const api = Get<performanceProps>(performanceURL + "/performance/" + id);

    return api
        .then((data) => {
            return { isOk: true, value: data } as Result<performanceProps, GetPerformanceByIdError>;
        }).catch((_) => {
            return { isOk: false, error: { message: "Performance not found" } } as Result<performanceProps, GetPerformanceByIdError>;
        });
}

export async function addPerformance(performance: performanceRequest) {
    const api = Post<performanceRequest>(performanceURL + "/performance/add", JSON.stringify(performance));

    return api
        .then((response) => {
            console.log(response);
        })
        .catch((err) => {
            console.log(err);
        });
}

export type DeletePerformanceByIdError = {
    //todo
}

export async function deletePerformanceById(id: number): Promise<Result<{}, DeletePerformanceByIdError>> {
    const api = Delete<any>(performanceURL + "/performance/delete/" + id);

    return api
        .catch((e) => {
            return { isOk: false, error: {} };
        });
}