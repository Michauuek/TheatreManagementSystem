import * as React from 'react';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';


type Props ={
    date:Date;
}

const DateButton = (props : Props) =>{

    const day_path : string =  props.date.getFullYear() + "-" + (props.date.getMonth() +1)+ "-" + props.date.getDate();
    const navigate = useNavigate();
    const navigateDay = () => {
        // 👇️ navigate to /
        navigate('/seances/' + day_path);
      };
    

    return(
        <Button variant='outline-secondary' onClick={navigateDay} >{props.date.getDate()}.{props.date.getMonth()+1}</Button> 
    )
}

export default DateButton