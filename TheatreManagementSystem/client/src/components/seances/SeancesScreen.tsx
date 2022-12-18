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
import { getExtendedSeancesByDate } from "../db/seanceAPI";
import { useParams } from "react-router-dom";
import PerformanceCard from "./PerformanceCard";
import DayFilter from "./DayFilter";
import { useLocation } from 'react-router-dom';

type Props = {
}
export type Seance ={
  seanceId : number,
  time : Date,
}
export type Seances ={
  performanceId: number,
  title :string,
  description : string,
  imageUrl : string,
  seance : Seance[],
}
export default function SeancesScreen(props : Props) {
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

  const seancesList = () =>{
    let seances : Seances[] =[];
    for(let i = 0; i < result.length; i++){
      let is_present : boolean = false
      let newDetails: Seance ={
        seanceId : result[i].id,
        time : new Date (result[i].seanceDate +"T"+ result[i].seanceTime),
      }
      // console.log(result[i].seanceDate + "T"+ result[i].seanceTime)
      for(let j = 0; j < seances.length; j++){
        if(seances[j].performanceId === result[i].performanceId){
          is_present=true
          seances[j].seance.push(newDetails)

        }
      }
      if(is_present === false){
        let newElement : Seances = {
          performanceId: result[i].performanceId,
          title: result[i].title,
          description: result[i].description,
          imageUrl: result[i].imageUrl,
          seance: [newDetails],
        }
        seances.push(newElement)
      }
    }
    return seances
  }

  const seances : Seances[] = seancesList()

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <Container>
        <div className="leading-header">
            <h1>Nadchodzące przedstawienia<br/>{date}</h1>
        </div>
        <DayFilter/>
          <Col>
            {seances?.map((value) => {
              return (
                <PerformanceCard
                  title={value.title}
                  description={value.description}
                  imageUrl={value.imageUrl}
                  seance = {value.seance}
                  performanceId = {value.performanceId}
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
