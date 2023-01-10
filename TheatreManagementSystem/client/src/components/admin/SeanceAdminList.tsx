import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformanceForm";
import { SeanceForm } from "./SeanceForm";
import SeanceItem from "./SeanceItem";
import React from "react";
import { AddSeance, getSeancesRangeByPerformanceId, seanceProps } from "../db/seanceAPI";
import { useLocation, useParams } from "react-router";
import { Box, TextField } from "@mui/material";
import { Col, Container, Row } from "react-bootstrap";



type Props = {
    // performanceId: number;
}

const SeanceAdminList = (props: Props) => {
  const params = useParams();
  let performanceId: number = -1;
  if (params.id !== undefined) {
    performanceId = parseInt(params.id);
  }



  let toDate = new Date();
  toDate.setDate(toDate.getDate() + 210);  
  const [result, setResult] = React.useState<seanceProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getSeancesRangeByPerformanceId(toDate, performanceId).then((data) => {
      setResult(data);
    });
  }, [location]);

  const deleteFromList = (id : number) =>{
    const updatedList = result?.filter((item) => item.id !== id);
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
            return (
            <>
            <SeanceItem
            seanceId={value.id!}
            title={value.performanceId.toString()}
            dateTime={new Date(value.seanceDate + "T" + value.seanceTime + "Z")}
            deleteFromList={deleteFromList}
            />
            </>
        );
        })}
                  </div>
              </Row>
            </Container>
          </Col>
          <Col
            md={4}
            className="admin-section border seance-list-section"
          >
            <SeanceForm onClickEvent = {(x) => {console.log(x); AddSeance(x);}}/>
          </Col>
        </Row>
      </Container>
    </div>
    <Footer/>
  </div>

  );
}

export default SeanceAdminList;




