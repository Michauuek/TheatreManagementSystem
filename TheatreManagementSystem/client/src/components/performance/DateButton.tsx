import * as React from 'react';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';

type Props ={
    date:Date;
}

const DateButton = (props : Props) =>{

    const buttonClicked = (date : Date) =>{
        
    }

    return(
        <Button variant='outline-secondary' onClick={() => buttonClicked(props.date)}>{props.date.getDate()}.{props.date.getMonth()+1}</Button> 
    )
}

export default DateButton