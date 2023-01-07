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
import { Col, Container, Row } from "react-bootstrap";
import "./style.css"


type Props = {
  seanceId: number,
  title: string,
}

const PerformanceItem = (props : Props) => {
  return(
    <Container className="border list-element">
        <Row>
            <Col>{props.seanceId}</Col>
        
            <Col>{props.title}</Col>
        
            <Col><Button variant="danger" onClick={() => deleteSeanceById(props.seanceId)}>Usuń</Button></Col>
        </Row>

    </Container>)
};

export default PerformanceItem