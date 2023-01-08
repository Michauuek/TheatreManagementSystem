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
import { Alert, Col, Container, Row } from "react-bootstrap";
import "./style.css"
import { useNavigate } from 'react-router-dom';
import { deletePerformanceById } from "../db/performanceAPI";
import redAlert from "../common/Alerts";


type Props = {
  performanceId: number,
  title: string,
  deleteFromList: (id: number) => void,
}



const PerformanceItem = (props : Props) => {
  const navigate = useNavigate();
  const navigateTo = () => {
    navigate('/admin/performance/' + props.performanceId);    
  };
  const [isError, setError] = React.useState<boolean>(false);


  async function deletePerformance(){
    try {
      const result = await deletePerformanceById(props.performanceId);
      console.log(result);
      props.deleteFromList(props.performanceId);
    } catch (error) {
      console.error(error);
      console.log("Failed to delete performance " +  props.performanceId + "!!")
      setError(true);
      setTimeout(() => {
        setError(false);
      }, 2000); 
      
    }
    


  }
  return(
    <Container className="border list-element">
      {isError && (redAlert("Nie możesz usunąć tego przedstawienia, upewnij się że usunąłeś wszystkie seanse"))}
        <Row>
            <Col>{props.performanceId}</Col>
        
            <Col>{props.title}</Col>
        
            <Col><Button  variant="danger" onClick={deletePerformance}>Usuń</Button> 
            <Button style={{margin: "10px"}} variant="secondary" onClick={navigateTo}>Przeglądaj</Button></Col>
        </Row>

    </Container>)
};

export default PerformanceItem