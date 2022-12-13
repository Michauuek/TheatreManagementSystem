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

import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import { getPerformance } from "../db/performanceAPI";
import { AddSeance, getSeances } from "../db/seanceAPI";
import { HallDisplay } from "../common/HallDisplay";
import { BrowserRouter } from "react-router-dom";
import PerformanceCard from "./PerformanceCard";
import DayFilter from "./DayFilter";

export default function PerformanceScreen() {
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
        <div className="leading-header">
            <h1>Nadchodzące przedstawienia</h1>
        </div>
        <DayFilter  setPage={setResult}/>
          <Col>
            {result?.map((value) => {
              return (
                <PerformanceCard
                  title={value.title}
                  description={value.description}
                  imageUrl={value.imageUrl}
                />
              );
            })}
          </Col>
        </Container>
      </div>
      <Footer />
    </div>
    
  );
}
