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


type Props = {
  seanceId: number,
  title: string,
  dateTime: Date
}

const SeanceItem = (props : Props) => {

  const navigate = useNavigate();
  const navigateTo = () => {
    navigate('/admin/seance/' + props.seanceId);    
  };
  return(   
    <Container className="border list-element">
        <Row>
            <Col>{props.seanceId}</Col>
        
            <Col>{props.title}</Col>
            <Col>{props.dateTime.toISOString()}</Col>
        
            <Col><Button  variant="danger" onClick={() => deleteSeanceById(props.seanceId)}>Usuń</Button> 
            <Button style={{margin: "10px"}} variant="secondary" onClick={navigateTo}>Rezerwacje</Button></Col>
        </Row>

    </Container>)
};

export default SeanceItem