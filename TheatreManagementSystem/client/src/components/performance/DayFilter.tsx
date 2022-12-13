import * as React from 'react';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import { performanceProps } from '../db/DBModel';
import DateButton from './DateButton';

type Props ={
    setPage: React.Dispatch<React.SetStateAction<performanceProps[]>>
}

const DayFilter = (props : Props) =>{

    const now = new Date();

    return(
    <ButtonToolbar aria-label="Toolbar with button groups">
      <ButtonGroup className="me-2" aria-label="First group">

        <DateButton date={now}/>
        <DateButton date={now}/>
        <DateButton date={now}/>
      </ButtonGroup>
    </ButtonToolbar>
    )
}

export default DayFilter