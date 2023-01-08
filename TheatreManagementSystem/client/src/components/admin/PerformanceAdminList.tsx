import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformanceForm";
import { SeanceForm } from "./SeanceForm";
import { performanceProps, seanceProps } from "../db/DBModel";
import SeanceItem from "./SeanceItem";
import React from "react";
import { getSeancesRangeByPerformanceId } from "../db/seanceAPI";
import { useLocation } from "react-router";
import { getPerformance } from "../db/performanceAPI";
import PerformanceItem from "./PerformanceItem";
import { Alert, Col, Container, Row } from "react-bootstrap";
import redAlert from "../common/Alerts";



type Props = {
}

const PerformanceAdminList = (props: Props) => {
  const [result, setResult] = React.useState<performanceProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getPerformance().then((data) => {
      setResult(data);
    });
  }, [location]);

  const deleteFromList = (id:number) =>{
    const updatedList = result?.filter((item) => item.performanceId !== id);
    console.log("delted :D")
    
    setResult(updatedList);
    console.log("updated:D")
    console.log(result)
  }

  

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">
      {/* <div className="alertContainerStyle">
        <Alert key='danger' variant='danger'>This is a 'danger' alert—check it out!</Alert>
      </div> */}
      {/* {redAlert("sdasds")} */}


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
                    <PerformanceItem
                    performanceId={value.performanceId}
                    title={value.title}
                    deleteFromList={deleteFromList}
                    />
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

export default PerformanceAdminList;