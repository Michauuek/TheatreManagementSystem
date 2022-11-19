import { useState, useEffect } from "react";

import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import { SeanceForm } from "./SeanceForm";
import { performanceProps, seanceProps } from "../db/dataBaseModel";
import { AddSeance, getSeances } from "../db/seanceAPI";
import Navbar from "../common/NavbarFun";
import NavbarFun from "../common/NavbarFun";
import { PerformanceCard } from "../performance/PerformanceCard";
import { getPerformance } from "../db/performanceAPI";
import './styles.css'

export default function Home() {
  const [result, setResult] = useState<performanceProps[]>([]);

  useEffect(() => {
    getPerformance().then((data) => {
      setResult(data);
    });
  }, []);

  return (
    <>
    <NavbarFun/>
    <div className="App">
      <Container>
        <Row>
        {result?.map((value) => {
          return (
            <PerformanceCard title={value.title} description={value.description} imageUrl={value.imageUrl}/>
          );
        })}
        </Row>
      </Container>

      <SeanceForm onClickEvent={(seance) => {
        console.log(seance);
        AddSeance(seance);
      }}/>
    </div>
    </>
  );
}
