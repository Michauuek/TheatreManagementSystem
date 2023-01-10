import * as React from "react";
import { useNavigate, useParams } from "react-router";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.css";

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

const PerformanceCard = (props: Props) => {
  const now = new Date();

  const performancePath: string = props.performanceId.toString();
  const navigate = useNavigate();

  const navigatePerformanceScreen = () => {
    navigate('/performance/' + performancePath);
  }

  return (
    <MDBCard className="card">
      <MDBRow className='g-0' size='2'>
        <MDBCol className="card-image-outer" md='2'>
          <div onClick={navigatePerformanceScreen} style={{
            backgroundImage: `url("${props.imageUrl}")`,
          }} className="card-image"/>
        </MDBCol>
        <MDBCol>
          <MDBCardBody>
            <MDBCol className="w-100" md="6">
              <div className="hour-section">
                <MDBCol><MDBCardTitle onClick={navigatePerformanceScreen} className="title">{props.title}</MDBCardTitle></MDBCol>
                <MDBCol>
                  {props.seance.map((value) => {
                    return (
                      <HourSeance seanceId={value.seanceId} time={value.time} />
                    );
                  })}
                </MDBCol>
              </div>
              <MDBCardText onClick={navigatePerformanceScreen} className="text">
                {props.description}
              </MDBCardText>
            </MDBCol>
          </MDBCardBody>
        </MDBCol>
      </MDBRow>
    </MDBCard>)
};

export default PerformanceCard

