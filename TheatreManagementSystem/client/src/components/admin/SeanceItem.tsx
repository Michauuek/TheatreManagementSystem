import * as React from "react";


import { useNavigate, useParams } from "react-router";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.css";
import { performanceProps } from "../db/DBModel";
import {
  MDBCard,
  MDBCardTitle,
  MDBCardBody,
  MDBRow,
  MDBCol
} from 'mdb-react-ui-kit';
import { deleteSeanceById } from "../db/seanceAPI";


type Props = {
  seanceId: number,
  title: string,
  dateTime: Date
}

const SeanceItem = (props : Props) => {
  return(
  <MDBCard className="card">
      <MDBRow className='g-0'>
        <MDBCol>
        <MDBCardBody >
          <div className="hour-section">
          <MDBCol><MDBCardTitle className="title">{"Id sensu: " + props.seanceId}</MDBCardTitle></MDBCol>
          <MDBCol><MDBCardTitle className="title">{"Tytuł: " + props.title}</MDBCardTitle></MDBCol>
          <MDBCol>{"Data: " + props.dateTime.toISOString()}</MDBCol>

          <Button variant="danger" onClick={() => deleteSeanceById(props.seanceId)}>Usuń</Button>
          </div>
          </MDBCardBody>
        </MDBCol>
      </MDBRow>
    </MDBCard>)
};

export default SeanceItem