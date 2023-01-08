import * as React from "react";


// import { useNavigate, useParams } from "react-router";
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
import { useNavigate } from 'react-router-dom';


type Props = {
  performanceId: number,
  title: string,
}

const ReservationItem = (props : Props) => {
  const navigate = useNavigate();
  const navigateTo = () => {
    navigate('/admin/performance/' + props.performanceId);    
  };
  return(
    <Container className="border list-element">
        <Row>
            <Col>{props.performanceId}</Col>
        
            <Col>{props.title}</Col>
        
            <Col><Button  variant="danger" onClick={() => deleteSeanceById(props.performanceId)}>Usuń</Button> 
            <Button style={{margin: "10px"}} variant="secondary" onClick={navigateTo}>Przeglądaj</Button></Col>
        </Row>

    </Container>)
};

export default ReservationItem