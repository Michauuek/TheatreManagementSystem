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

export const PerformanceCard: React.FunctionComponent<performanceProps> = (
  performanceProps
) => (
  <MDBCard className="card">
      <MDBRow className='g-0'>
        <MDBCol md='4' className="card-image-outer">
          <MDBCardImage className = "card-image" src={performanceProps.imageUrl} alt='...' fluid />
        </MDBCol>
        <MDBCol>
          <MDBCardBody >
            <MDBCardTitle className="title">{performanceProps.title}</MDBCardTitle>
            <MDBCardText className="text">
              {performanceProps.description}
            </MDBCardText>
          </MDBCardBody>
        </MDBCol>
      </MDBRow>
    </MDBCard>
);


