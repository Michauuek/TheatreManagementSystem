import { useState, useEffect } from "react";

import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";

import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import { SeanceForm } from "./SeanceForm";
import { performanceProps, seanceProps } from "../db/dataBaseModel";
import { AddSeance, getSeances } from "../db/seanceAPI";
import Navbar from "../common/NavbarFun";
import NavbarFun from "../common/NavbarFun";
import { PerformanceCard } from "../performance/PerformanceCard";
import { getPerformance } from "../db/performanceAPI";
import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { GoogleLogin} from "react-google-login";
import Cookies from "ts-cookies";

const clientId =
  "684105178392-12tts41fh93lbeo01u9hlji59i2ihor5.apps.googleusercontent.com";

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
        <SeanceForm
          onClickEvent={(seance: seanceProps) => {
            //AddSeance(seance);

            console.log(getSeances());
          }}
        />
        <GoogleLogin
          clientId={clientId}
          onSuccess={(credentialResponse) => {
            console.log(credentialResponse);
 
            const res = fetch("http://127.0.0.1:8080/seance/auth", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                id: credentialResponse.code,
              }),
            }).then((res) => {
              // read ReadableStream
              console.log(result);
            });
          }}
          onFailure={() => {
            console.log("Login Failed");
          }}
        />
      </div>

      <Footer />
    </div>
  );
}
