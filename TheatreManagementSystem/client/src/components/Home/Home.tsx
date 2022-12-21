import { useState, useEffect } from "react";

import Button from "@mui/material/Button";

import {
  hallProps, performanceProps,
  seanceProps,
  seatState
} from "../db/DBModel";
import NavbarFun from "../common/NavbarFun";
import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { getPerformance } from "../db/performanceAPI";
import { getSeances } from "../db/seanceAPI";
import { HallDisplay } from "../halls/HallDisplay";
import LoginButton from "../common/auth";

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data);
    });
  }, []);

  let testHalllayout: hallProps = {
    hallName: "Test Hall",
    background: "None",
    seats: [
      { seatName: "A1", posX: 0, posY: 0, state: seatState.RESERVED },
      { seatName: "A2", posX: 0, posY: 50, state: seatState.RESERVED },
      { seatName: "A3", posX: 0, posY: 100, state: seatState.RESERVED },
      { seatName: "A4", posX: 0, posY: 150, state: seatState.RESERVED },
      { seatName: "A5", posX: 100, posY: 0, state: seatState.RESERVED },
      { seatName: "A6", posX: 50, posY: 50, state: seatState.RESERVED },
      { seatName: "A7", posX: 50, posY: 100, state: seatState.RESERVED },
      { seatName: "A8", posX: 50, posY: 150, state: seatState.RESERVED },
      { seatName: "A9", posX: 100, posY: 0, state: seatState.RESERVED },
      { seatName: "A10", posX: 100, posY: 50, state: seatState.RESERVED },
      { seatName: "A11", posX: 100, posY: 100, state: seatState.RESERVED },
      { seatName: "A12", posX: 100, posY: 150, state: seatState.RESERVED },
    ],
  };

  let seanceInfo: seanceProps = {
    seanceDate: "2021-12-12",
    seanceTime: "12:00",
    hallName: "Test Hall",
    performanceId: 1,
  };

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <LoginButton/>

        <Button
          onClick={(x) => {
            getSeances();
          }}
        >
          Check
        </Button>
      </div>

      <Footer />
    </div>
  );
}
