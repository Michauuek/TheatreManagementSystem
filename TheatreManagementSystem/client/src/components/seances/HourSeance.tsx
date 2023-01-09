import * as React from "react";
import Button from "react-bootstrap/Button"
import { useNavigate } from "react-router-dom";
type Props = {
    seanceId: number;
    time: Date;
}

const HourSeance = (props : Props) =>{
    const seance_path : string = props.seanceId.toString();
    const navigate = useNavigate();
    const navigateSeance = () => {
        // 👇️ navigate to /
        navigate('/reserve/' + seance_path);
    };
    
    // const hour = props.time;
    const hour = props.time.toISOString().slice(11, 16);
    console.log(props.time + " ")

    return(
        <button type="button" onClick={navigateSeance} className="btn btn-outline-secondary hour-button">
            {hour}
        </button>
    )
}

export default HourSeance