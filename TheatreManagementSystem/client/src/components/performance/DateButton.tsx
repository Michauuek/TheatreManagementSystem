import * as React from 'react';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';

type Props ={
    date:Date;
}

const DateButton = (props : Props) =>{


    return(
        <Button variant='outline-secondary'>{props.date.getDate()}.{props.date.getMonth()+1}</Button> 
    )
}

export default DateButton