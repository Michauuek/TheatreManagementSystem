import * as React from "react";
import { useNavigate, useParams } from "react-router";
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
import { Seance } from "./SeancesScreen";

type Props = {
  performanceId: number,
  title: string,
  description: string,
  imageUrl: string,
  seance: Seance[],
}

const PerformanceCard = (props : Props) => {
  const now = new Date();

  const performancePath : string =  props.performanceId.toString();
  const navigate = useNavigate();

  const navigatePerformanceScreen = () =>{
    navigate('/performance/' + performancePath);
  }

  return(
  <MDBCard className="card">
      <MDBRow className='g-0' size='2'>
        <MDBCol md='4' className="card-image-outer">
          <MDBCardImage onClick={navigatePerformanceScreen} className = "card-image" src={props.imageUrl} alt='...' fluid />
        </MDBCol>
        <MDBCol>
        <MDBCardBody >
          <div className="hour-section">
          <MDBCol><MDBCardTitle onClick={navigatePerformanceScreen} className="title">{props.title}</MDBCardTitle></MDBCol>
          <MDBCol>
          {props.seance.map((value) => {
              return (
                <HourSeance seanceId={value.seanceId} time={value.time}/>
              );
            })}
          </MDBCol>
          </div>
            <MDBCardText onClick={navigatePerformanceScreen} className="text">
              {props.description}
            </MDBCardText>
          </MDBCardBody>
        </MDBCol>
      </MDBRow>
    </MDBCard>)
};

export default PerformanceCard

