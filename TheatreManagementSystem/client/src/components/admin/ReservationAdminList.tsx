import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformanceForm";
import { SeanceForm } from "./SeanceForm";
import SeanceItem from "./SeanceItem";
import React from "react";
import { getSeancesRangeByPerformanceId } from "../db/seanceAPI";
import { useLocation } from "react-router";
import { getPerformance, performanceProps } from "../db/performanceAPI";
import PerformanceItem from "./PerformanceItem";
import { Col, Container, Row } from "react-bootstrap";



type Props = {
}

const ReservationAdminList = (props: Props) => {
  const [result, setResult] = React.useState<performanceProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getPerformance().then((data) => {
      setResult(data.props);
    });
  }, [location]);

  const delteFromList = (id : number) =>{
    const updatedList = result?.filter((item) => item.performanceId !== id);
    setResult(updatedList);
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

                  <div>
                {result?.map((value) => {
                  console.log(value)
                    return (
                    <>
                    {/* <ReservationItem
                    performanceId={value.performanceId}
                    title={value.title}
                    /> */}
                    </>);})}
                    </div>
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