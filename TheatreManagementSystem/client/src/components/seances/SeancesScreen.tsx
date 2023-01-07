import { useState, useEffect } from "react";

import Container from "react-bootstrap/Container";
import Col from "react-bootstrap/Col";

import { seanceExtendedProps } from "../db/DBModel";
import NavbarFun from "../common/NavbarFun";

import "./styles.css";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import { getExtendedSeancesByDate } from "../db/seanceAPI";
import { useParams } from "react-router-dom";
import PerformanceCard from "./PerformanceCard";
import DayFilter from "./DayFilter";
import { useLocation } from "react-router-dom";
import { LoadingSpinner } from "../common/Loading";

type Props = {};

export type Seance = {
  seanceId: number;
  time: Date;
};
export type Seances = {
  performanceId: number;
  title: string;
  description: string;
  imageUrl: string;
  seance: Seance[];
};

function seancesList(result: seanceExtendedProps[]) {
  let seances: Seances[] = [];
  for (let i = 0; i < result.length; i++) {
    let is_present: boolean = false;
    let newDetails: Seance = {
      seanceId: result[i].id,
      time: new Date(result[i].seanceDate + "T" + result[i].seanceTime + "Z"),
    };
    console.log(result[i].seanceDate + "T"+ result[i].seanceTime)
    for (let j = 0; j < seances.length; j++) {
      if (seances[j].performanceId === result[i].performanceId) {
        is_present = true;
        seances[j].seance.push(newDetails);
      }
    }
    console.log("xd");
    if (is_present === false) {
      let newElement: Seances = {
        performanceId: result[i].performanceId,
        title: result[i].title,
        description: result[i].description,
        imageUrl: result[i].imageUrl,
        seance: [newDetails],
      };
      seances.push(newElement);
    }
    console.log("xdx");
  }
  return seances;
}

export default function SeancesScreen(props: Props) {
  const params = useParams();
  let date: string = "";
  if (typeof params.date !== "undefined") {
    date = params.date;
  }

  const [result, setResult] = useState<seanceExtendedProps[]>();

  const location = useLocation();
  useEffect(() => {
    getExtendedSeancesByDate(new Date(date)).then((data) => {
      setResult(data);
    });
  }, [location]);

  const seances: Seances[] = seancesList(result ? result : []);
  console.log(seances)

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <Container>
          <div className="leading-header">
            <h1>
              Nadchodzące przedstawienia
              <br />
              {date}
            </h1>
          </div>
          <DayFilter />
          <LoadingSpinner 
            isLoading={result==null}
            children={
              <Col>
              {seances?.map((value) => {
                console.log("Fucckkkkkk")
                return (
                  <PerformanceCard
                    title={value.title}
                    description={value.description}
                    imageUrl={value.imageUrl}
                    seance={value.seance}
                    performanceId={value.performanceId}
                  />
                );
              })}
            </Col>}
          />
        </Container>
      </div>
      <Footer />
    </div>
  );
}
