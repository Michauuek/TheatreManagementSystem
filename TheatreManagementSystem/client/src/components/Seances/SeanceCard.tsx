import * as React from 'react';
import { useParams } from 'react-router';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.css';

type CardProps = {
    title: string
}
export const SeanceCard: React.FunctionComponent<CardProps> = ({title}, {description}) => 
<aside>
    <Card style={{ width: '18rem' }}>
      <Card.Img variant="top" src="https://s.ciekawostkihistoryczne.pl/uploads/2020/10/Adam_Mickiewicz_-_Dziady_cz%C4%99%C5%9B%C4%87_I_II_i_IV_p053.png" />
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Card.Text>{description}</Card.Text>
        <Button variant="primary">Go</Button>
      </Card.Body>
    </Card>

</aside>



