import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformanceForm";
import { SeanceForm } from "./SeanceForm";
import { performanceProps, reservationProps, seanceProps } from "../db/DBModel";
import SeanceItem from "./SeanceItem";
import React from "react";
import { getSeancesRangeByPerformanceId } from "../db/seanceAPI";
import { useLocation, useParams } from "react-router";
import { getPerformance } from "../db/performanceAPI";
import PerformanceItem from "./PerformanceItem";
import { Col, Container, Row } from "react-bootstrap";
import { AllReservations, AllReservationsResponse, getReservationBySeanceId } from "../db/reservationAPI";
import { resourceLimits } from "worker_threads";



type Props = {
}


const ReservationAdminList = (props: Props) => {

  const params = useParams();
  let seanceId: number = -1;
  if (params.id !== undefined) {
    seanceId = parseInt(params.id);
  }
  // const [result, setResult] = React.useState<AllReservations>();
  const [reservationList, setListValue] = React.useState<reservationProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getReservationBySeanceId(seanceId).then((data) => {
      if(data.isOk){
        setListValue(data.reservations);
      }
    });
  }, [location]);

  const delteFromList = (id : number) =>{
    const updatedList = reservationList?.filter((item) => item.id !== id);
    setListValue(updatedList);
    
  }

  

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">


      <Container fluid={true}>
          <Row>
            <Col md={8}>
              <Container className="admin-section border">
                <Row>

                  
                </Row>
              </Container>
            </Col>
            <Col
              md={4}
              className="admin-section border seance-list-section"
            >
              <AddPerofrmanceForm/>
            </Col>
          </Row>
        </Container>
      </div>
      <Footer/>
    </div>

  );
}

export default ReservationAdminList;