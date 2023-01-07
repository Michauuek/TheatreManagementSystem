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

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data);
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
