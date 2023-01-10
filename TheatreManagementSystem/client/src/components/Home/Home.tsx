import { useState, useEffect } from "react";

import Button from "@mui/material/Button";

import NavbarFun from "../common/NavbarFun";
import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { getPerformance, performanceProps } from "../db/performanceAPI";
import LoginButton from "../common/Auth";
import { getReservationBySeanceId } from "../db/reservationAPI";

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data.value);
    });
  }, []);

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <LoginButton name="Login with google"/>

        <Button
          onClick={(x) => {
            getReservationBySeanceId(11)
            .then((data) => {
              if(data.isOk) {
                console.log(data.value);
              } else {
                console.log(data.error);
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
