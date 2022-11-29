import * as React from "react";
import { useParams } from "react-router";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import "bootstrap/dist/css/bootstrap.css";
import { performanceProps } from "../db/dataBaseModel";
import "./styles.css";

export const PerformanceCard: React.FunctionComponent<performanceProps> = (
  performanceProps
) => (
  <Card className="card" style={{ width: "12rem" }}>
    <Card.Img variant="top" src={performanceProps.imageUrl} />
    <Card.Body>
      <Card.Title>{performanceProps.title}</Card.Title>
      {/* <div className="cardText"><Card.Text>{performanceProps.description}</Card.Text></div> */}
      <Card.Text>
        <div className="cardText">{performanceProps.description}</div>
      </Card.Text>
    </Card.Body>
  </Card>
);
