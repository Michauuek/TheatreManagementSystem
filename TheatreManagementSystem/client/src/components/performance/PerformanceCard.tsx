import * as React from "react";
import { useParams } from "react-router";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.css";
import { performanceProps } from "../db/DBModel";
import "./styles.css";
import {
  MDBCard,
  MDBCardTitle,
  MDBCardText,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol
} from 'mdb-react-ui-kit';
import HourSeance from "./HourSeance";

const PerformanceCard: React.FunctionComponent<performanceProps> = (performanceProps) => {
  const now = new Date();
  return(
  <MDBCard className="card">
      <MDBRow className='g-0' size='2'>
        <MDBCol md='4' className="card-image-outer">
          <MDBCardImage className = "card-image" src={performanceProps.imageUrl} alt='...' fluid />
        </MDBCol>
        <MDBCol>
        <MDBCardBody >
          <div className="hour-section">
          <MDBCol><MDBCardTitle className="title">{performanceProps.title}</MDBCardTitle></MDBCol>
          <MDBCol>
          <HourSeance seanceId={12} time={now}/>
          <HourSeance seanceId={12} time={now}/>
          <HourSeance seanceId={12} time={now}/>
          <HourSeance seanceId={12} time={now}/>
          <HourSeance seanceId={12} time={now}/>
          </MDBCol>
          </div>
            <MDBCardText className="text">
              {performanceProps.description}
            </MDBCardText>
          </MDBCardBody>
        </MDBCol>
      </MDBRow>
    </MDBCard>)
};

export default PerformanceCard

