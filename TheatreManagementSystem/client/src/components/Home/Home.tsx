import { useState, useEffect } from "react";

import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import { seanceProps } from "../db/dataBaseModel";
import { SeanceForm } from "./SeanceForm";
import { getSeances } from "../db/seanceAPI";


function AddSeance(sance: seanceProps): void {
  fetch("http://localhost:3000/seances/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(sance),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Success:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

export default function Home() {
  const [result, setResult] = useState<seanceProps[]>([]);

  useEffect(() => {
    getSeances().then((data) => {
      setResult(data);
    });
  }, []);

  return (
    <div className="App">
      <h1>
        <div>Hello World</div>
        <Button variant="contained">Kurwa działa XD</Button>
        {result?.map((value) => {
          return (
            <div>
              <h1>Siema</h1>
              <div>{value.hallName}</div>
            </div>
          );
        })}
      </h1>

      <SeanceForm onClickEvent={(seance: seanceProps)=>{
        console.log(seance);
        AddSeance(seance);
      }}/>


    </div>
  );
}
