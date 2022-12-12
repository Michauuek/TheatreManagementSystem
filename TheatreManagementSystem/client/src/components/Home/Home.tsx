import { useState, useEffect } from "react";

import Button from "@mui/material/Button";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import { SeanceForm } from "../common/SeanceForm";
import {
  hallProps,
  hallWithSeance,
  performanceProps,
  seanceProps,
  seatState,
} from "../db/DBModel";
import NavbarFun from "../common/NavbarFun";
import { PerformanceCard } from "../performance/PerformanceCard";
import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import { getPerformance } from "../db/performanceAPI";
import { AddSeance, getSeances } from "../db/seanceAPI";
import { HallDisplay } from "../common/HallDisplay";

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data);
    });
  }, []);

  const googleLogin = useGoogleLogin({
    flow: "auth-code",
    onSuccess: async (codeResponse) => {
      // get tokens from backend
      const tokens = await axios.post("http://localhost:8081/auth/login", {
        id: codeResponse.code,
      });

      let userSession = tokens.headers["user_session"];

      // check if user session is undefined or null
      console.log(userSession);
      if (userSession === undefined) {
        throw new Error("Loggin failed");
      }

      // debug only - remove in production
      console.log(userSession);

      // set axios default
      axios.defaults.headers.common["user_session"] = userSession;
    },
    onError: (errorResponse) => console.log(errorResponse),
  });

  let testHalllayout: hallProps = {
    hallName: "Test Hall",
    backgroud: "None",
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
        <Container>
          <Row>
            {result?.map((value) => {
              return (
                <PerformanceCard
                  title={value.title}
                  description={value.description}
                  imageUrl={value.imageUrl}
                />
              );
            })}
          </Row>
        </Container>

        <HallDisplay hall={{ seanceInfo: seanceInfo, ...testHalllayout }} />

        <Button onClick={googleLogin}>Login with Google</Button>

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
