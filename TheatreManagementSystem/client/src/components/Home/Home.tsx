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
import LoginButton from "../common/Auth";
import { getReservationBySeanceId } from "../db/reservationAPI";

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data.props);
    });
  }, []);

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <LoginButton/>

        <Button
          onClick={(x) => {
            getReservationBySeanceId(11)
            .then((data) => {
              if(data.isOk) {
                console.log(data.reservations);
              } else {
                console.log(data.message);
              }
            })
          }}
        >
          Check
        </Button>
      </div>
      <Footer />
    </div>
  );
}
