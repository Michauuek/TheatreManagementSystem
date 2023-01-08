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
import redAlert from "../common/Alerts";


type Props = {
  seanceId: number,
  title: string,
  dateTime: Date,
  deleteFromList: (id: number) => void,
}

const SeanceItem = (props : Props) => {
  const [isError, setError] = React.useState<boolean>(false);
  async function deleteSeance(){
    try {
      const result = await deleteSeanceById(props.seanceId);
      console.log(result);
      props.deleteFromList(props.seanceId);
    } catch (error) {
      console.error(error);
      console.log("Failed to delete seance " +  props.seanceId + "!!")
      setError(true);
      setTimeout(() => {
        setError(false);
      }, 2000); 
      
    }
  }
    

  const navigate = useNavigate();
  const navigateTo = () => {
    navigate('/admin/seance/' + props.seanceId);    
  };
  return(   
    <Container className="border list-element">
      {isError && (redAlert("Nie możesz usunąć tego seansu"))}
        <Row>
            <Col>{props.seanceId}</Col>
        
            <Col>{props.title}</Col>
            <Col>{props.dateTime.toISOString()}</Col>
        
            <Col><Button  variant="danger" onClick={deleteSeance}>Usuń</Button> 
            <Button style={{margin: "10px"}} variant="secondary" onClick={navigateTo}>Rezerwacje</Button></Col>
        </Row>

    </Container>)
};

export default SeanceItem;