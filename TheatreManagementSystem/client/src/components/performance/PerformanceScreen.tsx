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
  seanceExtendedProps,
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
import { AddSeance, getExtendedSeancesByDate, getSeances } from "../db/seanceAPI";
import { HallDisplay } from "../common/HallDisplay";
import { BrowserRouter, useParams } from "react-router-dom";
import PerformanceCard from "./PerformanceCard";
import DayFilter from "./DayFilter";
import { useLocation } from 'react-router-dom';

type Props = {
}

export default function PerformanceScreen(props : Props) {
  // const [result, setResult] = useState<performanceProps[]>([]);

  // useEffect(() => {
  //   getPerformance().then((data) => {
  //     setResult(data);
  //   });
  // }, []);
  const params = useParams();
  let date : string = '';
  if(typeof params.date !== 'undefined'){
     date = params.date;
  }
 

  const [result, setResult] = useState<seanceExtendedProps[]>([]);

  const location = useLocation();
  useEffect(() => {
    getExtendedSeancesByDate(new Date(date)).then((data) => {
      setResult(data);
    });
  }, [location]);

  // useFocusEffect(
  //   React.useCallback(() => {
  //   getExtendedSeancesByDate(new Date(date)).then((data) => {
  //     setResult(data);
  //   });
  // }, []));


  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <Container>
        <div className="leading-header">
            <h1>Nadchodzące przedstawienia {date}</h1>
        </div>
        <DayFilter/>
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
