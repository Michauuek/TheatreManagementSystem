import axios from "axios";
import { Fails } from "../common/failable";
const performanceURL = "http://127.0.0.1:8084/performance";

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
export async function getPerformance(): Promise<Fails<{props: performanceProps[]}>> {
    const api = async () => {
        const data = await fetch(
            performanceURL + "/all",
            {
                method: "GET"
            }
        );
        return await data.json();
    };

    return api()
        .then((data) => {
            return {props: data as performanceProps[], isOk: true} as Fails<{props: performanceProps[]}>;
        })
        .catch((_) => {
            return {props: [], isOk: false, message: "Performance not found"} as Fails<{props: performanceProps[]}>;
        });
}

export async function getPerformanceById(id:number): Promise<performanceProps> {
    const api = async () => {
        const data = await fetch(
            performanceURL + "/" + id.toString(),
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

export async function addPerformance(performance: performanceRequest) {
    const api = async () => {
      const response = await fetch(
        performanceURL +"/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(performance),
      });
      return await response.json();
    };
  
    return api()
      .then((response) => {console.log(response);})
      .catch((err) => {console.log(err);});
  }

  export async function deletePerformanceById(id:number): Promise<String>{
    const api = async () => {
      const data = await fetch(
          performanceURL+ "/delete/" + id,
          {
              method: "DELETE"
          }
      );
      return await data.json();
    };
  
    return api()
      .then((data) => {
          return (data as String);
      })
      .catch((_) => {
          throw new Error("Performance not found");
      });
  }